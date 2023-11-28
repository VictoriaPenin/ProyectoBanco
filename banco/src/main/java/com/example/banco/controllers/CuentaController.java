package com.example.banco.controllers;


import com.example.banco.DTO.CuentaDTO;
import com.example.banco.services.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



    @RestController
    @RequestMapping("/cuentas")
    public class CuentaController {

        private final CuentaService cuentaService;

        public CuentaController(CuentaService cuentaService) {
            this.cuentaService = cuentaService;
        }

        @PostMapping("/crear")
        public ResponseEntity<String> saveCuenta(@RequestBody CuentaDTO cuentaDTO) {
            CuentaDTO savedCuentaDTO = cuentaService.saveCuenta(cuentaDTO);
            if (savedCuentaDTO != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Cuenta creada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos incorrectos");
            }
        }

        @GetMapping("/listar")
        public ResponseEntity<List<CuentaDTO>> getAllCuentas() {
            List<CuentaDTO> cuentasDTO = cuentaService.getAllCuentas();
            if (cuentasDTO.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cuentasDTO);
        }

        @GetMapping("/{id}")
        public ResponseEntity<CuentaDTO> findCuentaById(@PathVariable Long id) {
            CuentaDTO cuentaDTO = cuentaService.findCuentaById(id);
            if (cuentaDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cuentaDTO);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCuenta(@PathVariable Long id) {
            boolean deleted = cuentaService.deleteCuenta(id);
            if (deleted) {
                return ResponseEntity.ok("Cuenta eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta inexistente");
            }
        }

            @PostMapping("/transferir")
            public ResponseEntity<?> transferirDinero(@RequestParam Long idCuentaOrigen, @RequestParam Long idCuentaDestino, @RequestParam double monto) {
                CuentaDTO cuentaDTO = cuentaService.transferirDinero(idCuentaOrigen, idCuentaDestino, monto);
                if (cuentaDTO != null) {
                    return ResponseEntity.ok("Transferencia exitosa");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo realizar");
                }
            }

            @PostMapping("/depositar")
            public ResponseEntity<?> depositarDinero(@RequestParam Long idCuenta, @RequestParam double monto) {
                CuentaDTO cuentaDTO = cuentaService.depositarDinero(idCuenta, monto);
                if (cuentaDTO != null) {
                    return ResponseEntity.ok("Depósito exitoso");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo realizar");
                }
            }

            @PostMapping("/retirar")
            public ResponseEntity<?> retirarDinero(@RequestParam Long idCuenta, @RequestParam double monto) {
                CuentaDTO cuentaDTO = cuentaService.retirarDinero(idCuenta, monto);
                if (cuentaDTO != null) {
                    return ResponseEntity.ok("Retiro exitoso");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo realizar");
                }
            }
        }