package com.example.banco.controllers;

import com.example.banco.DTO.ClienteDTO;
import com.example.banco.DTO.CuentaDTO;
import com.example.banco.DTO.SucursalDTO;
import com.example.banco.services.SucursalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("sucursales")
public class SucursalController {

    private final SucursalService sucursalService;
    private final ModelMapper modelMapper;

    public SucursalController(SucursalService sucursalService, ModelMapper modelMapper) {
        this.sucursalService = sucursalService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("/crear")
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO nuevaSucursalDTO = sucursalService.crearSucursal(sucursalDTO);
        return new ResponseEntity<>(nuevaSucursalDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{sucursalId}/crear-cliente")
    public ResponseEntity<ClienteDTO> crearCliente(@PathVariable Long sucursalId, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoClienteDTO = sucursalService.crearClienteConCuenta(sucursalId, clienteDTO);
        if (nuevoClienteDTO != null) {
            return new ResponseEntity<>(nuevoClienteDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Puedes devolver un mensaje de error específico aquí.
        }
    }

    @GetMapping("/{sucursalId}/cuentas")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasPorSucursalId(@PathVariable Long sucursalId) {
        List<CuentaDTO> cuentasDTO = sucursalService.obtenerCuentasPorSucursalId(sucursalId).stream()
                .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(cuentasDTO, HttpStatus.OK);
    }

    @GetMapping("/{sucursalId}/clientes")
    public ResponseEntity<List<ClienteDTO>> obtenerClientesPorSucursalId(@PathVariable Long sucursalId) {
        List<ClienteDTO> clientesDTO = sucursalService.obtenerClientesPorSucursalId(sucursalId).stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
    }

    @PutMapping("/{sucursalId}/asociar-cuenta/{cuentaId}")
    public ResponseEntity<SucursalDTO> asociarCuentaASucursal(@PathVariable Long sucursalId, @PathVariable Long cuentaId) {
        SucursalDTO sucursalDTO = sucursalService.asociarCuentaASucursal(sucursalId, cuentaId);
        if (sucursalDTO != null) {
            return new ResponseEntity<>(sucursalDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
