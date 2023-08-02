package com.example.banco.services;

import com.example.banco.DTO.CuentaDTO;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.TipoTransaccion;
import com.example.banco.entities.Transaccion;
import com.example.banco.repositories.CuentaRepository;
import com.example.banco.repositories.TransaccionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    private final ModelMapper modelMapper;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, TransaccionRepository transaccionRepository, ModelMapper modelMapper) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CuentaDTO saveCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = modelMapper.map(cuentaDTO, Cuenta.class);
        cuenta = cuentaRepository.save(cuenta);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public List<CuentaDTO> getAllCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaDTO> cuentaDTOs = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            CuentaDTO cuentaDTO = modelMapper.map(cuenta, CuentaDTO.class);
            cuentaDTOs.add(cuentaDTO);
        }

        return cuentaDTOs;
    }


    @Override
    public CuentaDTO findCuentaById(Long id) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        return optionalCuenta.map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class)).orElse(null);
    }

    @Override
    public boolean deleteCuenta(Long id) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            cuentaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se encuentra ninguna cuenta con el ID " + id);
        }
        return false;
    }

    @Override
    public CuentaDTO transferirDinero(Long idCuentaOrigen, Long idCuentaDestino, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        Cuenta cuentaOrigen = cuentaRepository.findById(idCuentaOrigen)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada"));
        Cuenta cuentaDestino = cuentaRepository.findById(idCuentaDestino)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada"));

        if (cuentaOrigen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta de origen");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        return modelMapper.map(cuentaOrigen, CuentaDTO.class);
    }

    @Override
    public CuentaDTO depositarDinero(Long idCuenta, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        cuenta.setSaldo(cuenta.getSaldo() + monto);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(TipoTransaccion.DEPOSITO);
        transaccion.setIdCuentaDestino(cuenta.getId());
        transaccion.setMonto(monto);
        transaccionRepository.save(transaccion);

        cuentaRepository.save(cuenta);

        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO retirarDinero(Long idCuenta, double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta");
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(TipoTransaccion.EXTRACCION);
        transaccion.setIdCuentaDestino(cuenta.getId());
        transaccion.setMonto(-monto);
        transaccionRepository.save(transaccion);

        cuentaRepository.save(cuenta);

        return modelMapper.map(cuenta, CuentaDTO.class);
    }
}
