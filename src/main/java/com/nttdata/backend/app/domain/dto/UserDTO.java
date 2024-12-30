package com.nttdata.backend.app.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
     private String tipoDocumento;
     private String numeroDocumento;
     private String primerNombre;
     private String segundoNombre;
     private String primerApellido;
     private String segundoApellido;
     private String telefono;
     private String direccion;
     private String numeroCedula;
}
