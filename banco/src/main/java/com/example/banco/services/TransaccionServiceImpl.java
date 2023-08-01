package com.example.banco.services;


import com.example.banco.DTO.TransaccionDTO;
import com.example.banco.entities.Transaccion;
import com.example.banco.repositories.TransaccionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        // Convertir el TransaccionDTO a una entidad Transaccion
        Transaccion transaccion = modelMapper.map(transaccionDTO, Transaccion.class);

        // Guardar la transacción en el repositorio
        Transaccion savedTransaccion = transaccionRepository.save(transaccion);

        // Convertir la entidad guardada a un TransaccionDTO y retornarlo
        return modelMapper.map(savedTransaccion, TransaccionDTO.class);
    }

    @Override
    public List<TransaccionDTO> obtenerTransaccionesPorCuenta(Long idCuenta) {
        List<Transaccion> transacciones = transaccionRepository.findByCuentaId(idCuenta);
        return transacciones.stream()
                .map(transaccion -> modelMapper.map(transaccion, TransaccionDTO.class))
                .collect(Collectors.toList());
    }
}
