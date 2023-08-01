package com.example.banco.services;


import com.example.banco.DTO.CuentaDTO;

import java.util.List;


public interface CuentaService {
    CuentaDTO saveCuenta(CuentaDTO cuentaDTO);
    List<CuentaDTO> getAllCuentas();
    CuentaDTO findCuentaById(Long id);
    void deleteCuenta(Long id);

    CuentaDTO transferirDinero(Long idCuentaOrigen, Long idCuentaDestino, double monto);

    CuentaDTO depositarDinero(Long idCuenta, double monto);

    CuentaDTO retirarDinero(Long idCuenta, double monto);
}