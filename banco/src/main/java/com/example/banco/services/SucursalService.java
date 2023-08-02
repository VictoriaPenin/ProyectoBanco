package com.example.banco.services;


import com.example.banco.DTO.SucursalDTO;

import java.util.List;

public interface SucursalService {
    SucursalDTO saveSucursal(SucursalDTO sucursalDTO);
    List<SucursalDTO> getAllSucursales();
    SucursalDTO findSucursalById(Long id);
    void deleteSucursal(Long id);

    SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO);
}
