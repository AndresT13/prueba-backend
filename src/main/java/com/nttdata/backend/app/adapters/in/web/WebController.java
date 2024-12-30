package com.nttdata.backend.app.adapters.in.web;

import com.nttdata.backend.app.domain.dto.UserDTO;
import com.nttdata.backend.app.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final ClientService clientService;

    @Autowired
    public WebController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/ingreso")
    public String ingresoForm(Model model) {
        model.addAttribute("cliente", new UserDTO());
        return "ingreso";
    }

    @PostMapping("/buscar")
    public String buscarCliente(@RequestParam String tipoDocumento, @RequestParam String numeroDocumento, Model model) {
        UserDTO cliente = clientService.getClient(tipoDocumento, numeroDocumento);
        model.addAttribute("cliente", cliente);
        return "resumen";
    }
}
