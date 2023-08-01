package com.example.banco.controllers;


import com.example.banco.DTO.SucursalDTO;
import com.example.banco.services.SucursalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;
    private final ModelMapper modelMapper;

    public SucursalController(SucursalService sucursalService, ModelMapper modelMapper) {
        this.sucursalService = sucursalService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/crear")
    public ResponseEntity<SucursalDTO> saveSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO savedSucursalDTO = sucursalService.saveSucursal(sucursalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSucursalDTO);
    }

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getAllSucursales() {
        List<SucursalDTO> sucursalDTOs = sucursalService.getAllSucursales();
        return ResponseEntity.ok(sucursalDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> findSucursalById(@PathVariable Long id) {
        SucursalDTO sucursalDTO = sucursalService.findSucursalById(id);
        if (sucursalDTO != null) {
            return ResponseEntity.ok(sucursalDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
