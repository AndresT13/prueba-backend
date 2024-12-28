package com.nttdata.backend.app.adapters.in.web;

import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public UserDTO getClient(@RequestParam String type, @RequestParam String number) {
        return clientService.getClient(type, number);
    }
}
