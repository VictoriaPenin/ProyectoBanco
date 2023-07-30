package com.example.banco.controllers;

import com.example.banco.DTO.ClienteDTO;
import com.example.banco.DTO.CuentaDTO;
import com.example.banco.DTO.SucursalDTO;
import com.example.banco.entities.Cliente;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Sucursal;
import com.example.banco.services.SucursalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/crear")
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO nuevaSucursalDTO = sucursalService.crearSucursal(sucursalDTO);
        return new ResponseEntity<>(nuevaSucursalDTO, HttpStatus.CREATED);
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
