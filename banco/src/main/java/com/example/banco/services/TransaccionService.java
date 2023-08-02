package com.example.banco.services;

import com.example.banco.DTO.TransaccionDTO;
import com.example.banco.entities.Transaccion;

import java.util.List;

public interface TransaccionService {
    TransaccionDTO saveTransaccion(TransaccionDTO transaccionDTO);

    List<TransaccionDTO> obtenerTransaccionesPorCuenta(Long idCuenta);
}
