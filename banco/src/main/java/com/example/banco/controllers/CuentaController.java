package com.example.banco.controllers;

import com.example.banco.DTO.CuentaDTO;
import com.example.banco.services.CuentaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("cuentas")
public class CuentaController {

    private final CuentaService cuentaService;
    private final ModelMapper modelMapper;

    public CuentaController(CuentaService cuentaService, ModelMapper modelMapper) {
        this.cuentaService = cuentaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/crear")
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO nuevaCuentaDTO = cuentaService.crearCuenta(cuentaDTO);
        return new ResponseEntity<>(nuevaCuentaDTO, HttpStatus.CREATED);
    }

    @PostMapping("/transferencias")
    public ResponseEntity<String> realizarTransferencia(@RequestParam Long cuentaOrigenId,
                                                        @RequestParam Long cuentaDestinoId,
                                                        @RequestParam double monto) {
        cuentaService.realizarTransferencia(cuentaOrigenId, cuentaDestinoId, monto);
        return new ResponseEntity<>("Transferencia realizada con éxito", HttpStatus.OK);
    }


 /*   @PostMapping("/plazofijo/{cuentaId}")
    public ResponseEntity<CuentaDTO> crearPlazoFijo(@PathVariable Long cuentaId,
                                                    @RequestParam double monto,
                                                    @RequestParam int plazoEnDias) {
        CuentaDTO cuentaDTO = cuentaService.crearPlazoFijo(cuentaId, monto, plazoEnDias);
        if (cuentaDTO != null) {
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/


    @PostMapping("/extraer/{cuentaId}")
    public ResponseEntity<CuentaDTO> extraerDinero(@PathVariable Long cuentaId,
                                                   @RequestParam double monto) {
        CuentaDTO cuentaDTO = cuentaService.extraerDinero(cuentaId, monto);
        if (cuentaDTO != null) {
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Podrías devolver un mensaje de error específico aquí.
        }
    }

    @PostMapping("/ingresar/{cuentaId}")
    public ResponseEntity<CuentaDTO> ingresarDinero(@PathVariable Long cuentaId,
                                                    @RequestParam double monto) {
        CuentaDTO cuentaDTO = cuentaService.ingresarDinero(cuentaId, monto);
        if (cuentaDTO != null) {
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/crear/{clienteId}")
    public ResponseEntity<CuentaDTO> crearCuenta(@PathVariable Long clienteId, @RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO nuevaCuentaDTO = cuentaService.crearCuenta(clienteId, cuentaDTO);
        return new ResponseEntity<>(nuevaCuentaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasPorClienteId(@PathVariable Long clienteId) {
        List<CuentaDTO> cuentasDTO = cuentaService.obtenerCuentasPorClienteId(clienteId).stream()
                .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(cuentasDTO, HttpStatus.OK);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasPorSucursalId(@PathVariable Long sucursalId) {
        List<CuentaDTO> cuentasDTO = cuentaService.obtenerCuentasPorSucursalId(sucursalId).stream()
                .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(cuentasDTO, HttpStatus.OK);
    }

    @PutMapping("/{cuentaId}/asociar-cliente/{clienteId}")
    public ResponseEntity<CuentaDTO> asociarCuentaACliente(@PathVariable Long cuentaId, @PathVariable Long clienteId) {
        CuentaDTO cuentaDTO = cuentaService.asociarCuentaACliente(cuentaId, clienteId);
        if (cuentaDTO != null) {
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{cuentaId}/asociar-sucursal/{sucursalId}")
    public ResponseEntity<CuentaDTO> asociarCuentaASucursal(@PathVariable Long cuentaId, @PathVariable Long sucursalId) {
        CuentaDTO cuentaDTO = cuentaService.asociarCuentaASucursal(cuentaId, sucursalId);
        if (cuentaDTO != null) {
            return new ResponseEntity<>(cuentaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
