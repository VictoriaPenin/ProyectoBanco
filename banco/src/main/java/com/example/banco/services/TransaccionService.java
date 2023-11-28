package com.example.banco.services;

import com.example.banco.DTO.TransaccionDTO;
import com.example.banco.entities.Transaccion;

import java.util.List;

public interface TransaccionService {
    TransaccionDTO saveTransaccion(TransaccionDTO transaccionDTO);

    List<TransaccionDTO> obtenerTransaccionesPorCuenta(Long idCuenta);

    List<TransaccionDTO> obtenerTransaccionesPorCliente(Long idCliente);

    List<TransaccionDTO> obtenerTransaccionesPorCuentaYSucursal(Long idCuenta, Long idSucursal);

    // Método para crear un plazo fijo
    TransaccionDTO crearPlazoFijo(TransaccionDTO transaccionDTO);

}

