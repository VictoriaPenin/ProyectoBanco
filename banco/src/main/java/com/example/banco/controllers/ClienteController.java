package com.example.banco.controllers;


import com.example.banco.DTO.ClienteDTO;
import com.example.banco.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    public ClienteController(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO savedClienteDTO = clienteService.saveCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClienteDTO);
    }

    @GetMapping("/traer")
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clienteDTOs = clienteService.getAllClientes();
        return ResponseEntity.ok(clienteDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findClienteById(id);
        if (clienteDTO != null) {
            return ResponseEntity.ok(clienteDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
