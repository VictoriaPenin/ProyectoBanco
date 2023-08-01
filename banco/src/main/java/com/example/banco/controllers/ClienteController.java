package com.example.banco.controllers;

import com.example.banco.DTO.ClienteDTO;
import com.example.banco.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    public ClienteController(ClienteService clienteService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }



    //crear cliente
    @PostMapping("/crear")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoClienteDTO = clienteService.crearCliente(clienteDTO);
        return new ResponseEntity<>(nuevoClienteDTO, HttpStatus.CREATED);
    }

    //traer un cliente por su id
    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long clienteId) {
        ClienteDTO clienteDTO = clienteService.obtenerClientePorId(clienteId);
        if (clienteDTO != null) {
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //listar todos los clientes
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientesDTO = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
    }

    //actualizar cliente usando su id
    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long clienteId, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizadoDTO = clienteService.actualizarCliente(clienteId, clienteDTO);
        if (clienteActualizadoDTO != null) {
            return new ResponseEntity<>(clienteActualizadoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //eliminar cliente por id
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<String> eliminarClientePorId(@PathVariable Long clienteId) {
        boolean eliminado = clienteService.eliminarClientePorId(clienteId);
        if (eliminado) {
            return new ResponseEntity<>("Cliente eliminado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo encontrar el cliente con el ID especificado", HttpStatus.NOT_FOUND);
        }
    }
}
