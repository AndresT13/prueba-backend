package com.nttdata.backend.app.domain.service;

import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.exception.NotFoundException;
import com.nttdata.backend.app.exception.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ClientService {

    private static final Logger logger = LogManager.getLogger(ClientService.class);
    private static final Map<String, UserDTO> CLIENTS = new HashMap<>();

    static {
        CLIENTS.put("C23445322", new UserDTO("Juan", "Carlos", "Perez", "Gomez", "1234567890", "Calle 123", "23445322"));
        CLIENTS.put("P987654321", new UserDTO("Maria", "Luisa", "Rodriguez", "Martinez", "0987654321", "Carrera 456", "987654321"));
    }

    public UserDTO getClient(String type, String number) {
        if (!"C".equals(type) && !"P".equals(type)) {
            logger.warn("Invalid document type: {}", type);
            throw new BadRequestException("Invalid document type. It must be 'C' or 'P'.");
        }
        String key = type + number;
        logger.info("Searching for client with key: {}", key);
        System.out.println("\n" +
                "Searching for client with password: " + key); // Añadir logging para depuración
        UserDTO client = CLIENTS.get(key);
        if (client == null) {
            logger.error("Client not found with key: {}", key);
            throw new NotFoundException("Client not found.");
        }
        logger.info("Client found: {}", client);
        return client;
    }
}
