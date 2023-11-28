package com.example.banco.services;

import com.example.banco.DTO.SucursalDTO;
import com.example.banco.entities.Sucursal;
import com.example.banco.repositories.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class SucursalServiceImpl implements SucursalService {

    //constructor para inyectar dependencias en vez de usar @Autowired
    private final SucursalDTO sucursalDTO;

    private final SucursalRepository sucursalRepository;
    private final ModelMapper modelMapper;

    public SucursalServiceImpl(SucursalDTO sucursalDTO, SucursalRepository sucursalRepository, ModelMapper modelMapper) {
        this.sucursalDTO = sucursalDTO;
        this.sucursalRepository = sucursalRepository;
        this.modelMapper = modelMapper;
    }

    //crear sucursal
    @Override
    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = modelMapper.map(sucursalDTO, Sucursal.class);
        sucursal = sucursalRepository.save(sucursal);
        return modelMapper.map(sucursal, SucursalDTO.class);
    }


//listar sucursales
    @Override
    public List<SucursalDTO> getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        List<SucursalDTO> sucursalDTOs = new ArrayList<>();

        for (Sucursal sucursal : sucursales) {
            SucursalDTO sucursalDTO = modelMapper.map(sucursal, SucursalDTO.class);
            sucursalDTOs.add(sucursalDTO);
        }

        return sucursalDTOs;
    }



    //optional devuelve null si no encuentra el valor, en vez de manejar una excepcion
    @Override
    public SucursalDTO findSucursalById(Long id) {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);
        return optionalSucursal.map(sucursal -> modelMapper.map(sucursal, SucursalDTO.class)).orElse(null);
    }

    //eliminar
    @Override
    public void deleteSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }

    //actualizar
    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursalDTO) {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);
        if (optionalSucursal.isPresent()) {
            Sucursal existingSucursal = optionalSucursal.get();
            existingSucursal.setNombre(sucursalDTO.getNombre());
            existingSucursal.setDomicilio(sucursalDTO.getDomicilio());

            Sucursal updatedSucursal = sucursalRepository.save(existingSucursal);
            return modelMapper.map(updatedSucursal, SucursalDTO.class);
        } else {
            return null;
        }
    }}