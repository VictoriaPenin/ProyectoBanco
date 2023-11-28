package com.example.banco.controllers;

import com.example.banco.DTO.SucursalDTO;
import com.example.banco.DTO.TransaccionDTO;
import com.example.banco.services.TransaccionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")

public class TransaccionController {
/*
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        try {
            // Si el tipo de transacción es "PLAZO_FIJO", llamamos al método para crear el plazo fijo
            if ("PLAZO_FIJO".equals(transaccionDTO.getTipoTransaccion())) {
                TransaccionDTO result = transaccionService.crearPlazoFijo(transaccionDTO);
                return ResponseEntity.ok(String.valueOf(result));
            } else {
                // Si es otro tipo de transacción, simplemente lo guardamos
                TransaccionDTO result = transaccionService.saveTransaccion(transaccionDTO);
                return ResponseEntity.ok(String.valueOf(result));
            }
        } catch (ConstraintViolationException ex) {
            // Manejo de la excepción de validación
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                errorMessage.append(violation.getMessage()).append("\n");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        } catch (Exception e) {
            // Manejo de otras excepciones (opcional)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la transacción.");
        }
    }

    @GetMapping("/cuenta/{idCuenta}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorCuenta(@PathVariable Long idCuenta) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorCuenta(idCuenta);
        return ResponseEntity.ok(transacciones);
    }


    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorCliente(@PathVariable Long idCliente) {
        List<TransaccionDTO> transaccionesDTO = transaccionService.obtenerTransaccionesPorCliente(idCliente);
        return ResponseEntity.ok(transaccionesDTO);
    }




    @GetMapping("/cuenta-sucursal/{idCuenta}/{idSucursal}")
    public ResponseEntity<List<TransaccionDTO>> obtenerTransaccionesPorCuentaYSucursal(@PathVariable Long idCuenta, @PathVariable Long idSucursal) {
        List<TransaccionDTO> transacciones = transaccionService.obtenerTransaccionesPorCuentaYSucursal(idCuenta, idSucursal);
        return ResponseEntity.ok(transacciones);
    }*/

}