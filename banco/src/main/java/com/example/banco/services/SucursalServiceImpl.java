package com.example.banco.services;

import com.example.banco.DTO.SucursalDTO;
import com.example.banco.entities.Sucursal;
import com.example.banco.repositories.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    public SucursalServiceImpl(SucursalRepository sucursalRepository, ModelMapper modelMapper) {
        this.sucursalRepository = sucursalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = modelMapper.map(sucursalDTO, Sucursal.class);
        sucursal = sucursalRepository.save(sucursal);
        return modelMapper.map(sucursal, SucursalDTO.class);
    }

    @Override
    public List<SucursalDTO> getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales.stream()
                .map(sucursal -> modelMapper.map(sucursal, SucursalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SucursalDTO findSucursalById(Long id) {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);
        if (optionalSucursal.isPresent()) {
            return modelMapper.map(optionalSucursal.get(), SucursalDTO.class);
        }
        return null;
    }

    @Override
    public void deleteSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }
}
