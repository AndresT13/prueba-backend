# Usar una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Instalar Maven
RUN apt-get update \
    && apt-get install -y maven \
    && apt-get clean

# Establecer el directorio de trabajo para la fase de compilación
WORKDIR /app

# Copiar el archivo de configuración de Maven y las dependencias
COPY pom.xml .

# Descargar las dependencias sin construir el proyecto
RUN mvn dependency:go-offline -B

# Copiar el resto del proyecto y construir el archivo JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Verificar que el archivo JAR se haya generado
RUN ls -l /app/target

# Crear una segunda fase para una imagen más ligera
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR construido desde la fase anterior
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación Spring Boot escucha (por defecto 8090)
EXPOSE 8090

# Configurar variables de entorno si es necesario
ENV SPRING_PROFILES_ACTIVE=prod

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar", "--server.port=8090"]
