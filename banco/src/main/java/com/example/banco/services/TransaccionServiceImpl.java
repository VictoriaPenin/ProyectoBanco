package com.example.banco.services;

import com.example.banco.DTO.TransaccionDTO;
import com.example.banco.entities.Transaccion;
import com.example.banco.repositories.TransaccionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final ModelMapper modelMapper;

    public TransaccionServiceImpl(TransaccionRepository transaccionRepository, ModelMapper modelMapper) {
        this.transaccionRepository = transaccionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransaccionDTO saveTransaccion(TransaccionDTO transaccionDTO) {

        Transaccion transaccion = modelMapper.map(transaccionDTO, Transaccion.class);  //copia las prop de transacciondto al objeto transaccion


        Transaccion savedTransaccion = transaccionRepository.save(transaccion);  //guarda la transaccion en la base de datos


        return modelMapper.map(savedTransaccion, TransaccionDTO.class);  //muestra la transaccion que se guardo
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCuenta(Long idCuenta) {
        List<Transaccion> transacciones = transaccionRepository.findByCuentaId(idCuenta);
        List<TransaccionDTO> transaccionDTOs = new ArrayList<>();

        for (Transaccion transaccion : transacciones) {
            TransaccionDTO transaccionDTO = modelMapper.map(transaccion, TransaccionDTO.class);
            transaccionDTOs.add(transaccionDTO);
        }

        return transaccionDTOs;
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCliente(Long idCliente) {
        return null;
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCuentaYSucursal(Long idCuenta, Long idSucursal) {
        return null;
    }

    @Override
    public TransaccionDTO crearPlazoFijo(TransaccionDTO transaccionDTO) {
        return null;
    }


    /*
    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCliente(Long idCliente) {
        List<Transaccion> transacciones = transaccionRepository.findByClienteId(idCliente);
        List<TransaccionDTO> transaccionDTOs = new ArrayList<>();

        for (Transaccion transaccion : transacciones) {
            TransaccionDTO transaccionDTO = modelMapper.map(transaccion, TransaccionDTO.class);
            transaccionDTOs.add(transaccionDTO);
        }

        return transaccionDTOs;
    }


    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCuentaYSucursal(Long idCuenta, Long idSucursal) {
        List<Transaccion> transacciones = transaccionRepository.findByCuentaIdAndIdSucursal(idCuenta, idSucursal);
        List<TransaccionDTO> transaccionDTOs = new ArrayList<>();

        for (Transaccion transaccion : transacciones) {
            TransaccionDTO transaccionDTO = modelMapper.map(transaccion, TransaccionDTO.class);
            transaccionDTOs.add(transaccionDTO);
        }

        return transaccionDTOs;
    }

    @Override
    public TransaccionDTO crearPlazoFijo(TransaccionDTO transaccionDTO) {
        return null;
    }
*/
}
