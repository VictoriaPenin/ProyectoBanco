package com.example.banco.controllers;


import com.example.banco.DTO.ClienteDTO;
import com.example.banco.exceptions.NotFoundException;
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

    //CREAR CLIENTES
    @PostMapping("/crear")
    public ResponseEntity<String> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO savedClienteDTO = clienteService.saveCliente(clienteDTO);
        if (savedClienteDTO != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear el cliente");
        }
    }

    //LISTAR TODOS LOS CLIENTES
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clienteDTOs = clienteService.getAllClientes();
        return ResponseEntity.ok(clienteDTOs);
    }




    //LISTAR UN CLIENTE POR SU ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findClienteById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findClienteById(id);
        if (clienteDTO != null) {
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un cliente asociado a ese id");
        }
    }
    //ELIMINAR UN CLIENTE POR SU ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.ok("Cliente eliminado correctamente.");
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    //EDITAR UN CLIENTE BUSCANDO POR ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO updatedClienteDTO = clienteService.updateCliente(id, clienteDTO);
        if (updatedClienteDTO != null) {
            return ResponseEntity.ok("Cliente actualizado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un cliente asociado a ese id");
        }
    }
}
