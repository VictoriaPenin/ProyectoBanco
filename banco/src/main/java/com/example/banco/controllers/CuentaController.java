package com.example.banco.controllers;

import com.example.banco.DTO.CuentaDTO;
import com.example.banco.DTO.TransaccionDTO;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Transaccion;
import com.example.banco.services.CuentaService;
import com.example.banco.services.TransaccionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;
    private final TransaccionService transaccionService;
    private final ModelMapper modelMapper;

    public CuentaController(CuentaService cuentaService, TransaccionService transaccionService, ModelMapper modelMapper) {
        this.cuentaService = cuentaService;
        this.transaccionService = transaccionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/crear")
    public ResponseEntity<CuentaDTO> saveCuenta(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO savedCuentaDTO = cuentaService.saveCuenta(cuentaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCuentaDTO);
    }

    @GetMapping("/traer")
    public ResponseEntity<List<CuentaDTO>> getAllCuentas() {
        List<CuentaDTO> cuentaDTOs = cuentaService.getAllCuentas();
        return ResponseEntity.ok(cuentaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> findCuentaById(@PathVariable Long id) {
        CuentaDTO cuentaDTO = cuentaService.findCuentaById(id);
        if (cuentaDTO != null) {
            return ResponseEntity.ok(cuentaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idCuenta}/transacciones")
    public ResponseEntity<TransaccionDTO> crearTransaccion(@PathVariable Long idCuenta, @RequestBody TransaccionDTO transaccionDTO) {
        CuentaDTO cuentaDTO = cuentaService.findCuentaById(idCuenta);
        if (cuentaDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // Convertir el TransaccionDTO a una entidad Transaccion
        Transaccion transaccion = modelMapper.map(transaccionDTO, Transaccion.class);
        transaccion.setCuenta(modelMapper.map(cuentaDTO, Cuenta.class));

        // Guardar la transacción en el servicio
        TransaccionDTO createdTransaccionDTO = transaccionService.saveTransaccion(transaccionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaccionDTO);
    }

    @GetMapping("/{idCuenta}/transacciones")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesDeCuenta(@PathVariable Long idCuenta) {
        List<TransaccionDTO> transaccionesDTO = transaccionService.obtenerTransaccionesPorCuenta(idCuenta);
        if (transaccionesDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transaccionesDTO);
    }
}
