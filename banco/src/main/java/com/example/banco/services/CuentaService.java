package com.example.banco.services;


import com.example.banco.DTO.CuentaDTO;
import com.example.banco.entities.Cliente;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Sucursal;
import com.example.banco.mapper.ModelMapperConfig;
import com.example.banco.repositories.ClienteRepository;
import com.example.banco.repositories.CuentaRepository;
import com.example.banco.repositories.SucursalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = modelMapper.map(cuentaDTO, Cuenta.class);
        cuenta = cuentaRepository.save(cuenta);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    public List<CuentaDTO> obtenerCuentasPorClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente != null) {
            List<Cuenta> cuentas = cuentaRepository.findByCliente(cliente);
            return cuentas.stream()
                    .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public List<CuentaDTO> obtenerCuentasPorSucursalId(Long sucursalId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);
        if (sucursal != null) {
            List<Cuenta> cuentas = cuentaRepository.findBySucursal(sucursal);
            return cuentas.stream()
                    .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public CuentaDTO asociarCuentaACliente(Long cuentaId, Long clienteId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cuenta != null && cliente != null) {
            cuenta.setCliente(cliente);
            cuenta = cuentaRepository.save(cuenta);
            return modelMapper.map(cuenta, CuentaDTO.class);
        } else {
            return null;
        }
    }

    public CuentaDTO asociarCuentaASucursal(Long cuentaId, Long sucursalId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);

        if (cuenta != null && sucursal != null) {
            cuenta.setSucursal(sucursal);
            cuenta = cuentaRepository.save(cuenta);
            return modelMapper.map(cuenta, CuentaDTO.class);
        } else {
            return null;
        }
    }

    public void realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, double monto) {
        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId).orElse(null);
        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId).orElse(null);

        if (cuentaOrigen != null && cuentaDestino != null) {
            double saldoOrigen = cuentaOrigen.getSaldo();
            if (saldoOrigen >= monto) {
                cuentaOrigen.setSaldo(saldoOrigen - monto);
                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

                cuentaRepository.save(cuentaOrigen);
                cuentaRepository.save(cuentaDestino);
            } else {
                throw new IllegalArgumentException("Fondos insuficientes en la cuenta de origen.");
            }
        } else {
            throw new IllegalArgumentException("Cuenta de origen o cuenta de destino no encontrada.");
        }
    }

    public CuentaDTO crearCuenta(Long clienteId, CuentaDTO cuentaDTO) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente != null) {
            Cuenta cuenta = modelMapper.map(cuentaDTO, Cuenta.class);
            cuenta.setCliente(cliente);
            cuenta = cuentaRepository.save(cuenta);
            return modelMapper.map(cuenta, CuentaDTO.class);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + clienteId);
        }
    }
}
