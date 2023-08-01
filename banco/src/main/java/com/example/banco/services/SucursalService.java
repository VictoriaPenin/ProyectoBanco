package com.example.banco.services;

import com.example.banco.DTO.ClienteDTO;
import com.example.banco.DTO.CuentaDTO;
import com.example.banco.DTO.SucursalDTO;
import com.example.banco.entities.Cliente;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Sucursal;
import com.example.banco.entities.TipoCuenta;
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
public class SucursalService {

    private SucursalRepository sucursalRepository;
    private CuentaRepository cuentaRepository;
    private ModelMapper modelMapper;
    private ClienteRepository clienteRepository;

    // Constructor parametrizado para inyectar las dependencias
    public SucursalService(SucursalRepository sucursalRepository, CuentaRepository cuentaRepository,
                           ModelMapper modelMapper, ClienteRepository clienteRepository) {
        this.sucursalRepository = sucursalRepository;
        this.cuentaRepository = cuentaRepository;
        this.modelMapper = modelMapper;
        this.clienteRepository = clienteRepository;
    }

    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = modelMapper.map(sucursalDTO, Sucursal.class);
        sucursal = sucursalRepository.save(sucursal);
        return modelMapper.map(sucursal, SucursalDTO.class);
    }

    public List<CuentaDTO> obtenerCuentasPorSucursalId(Long sucursalId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);
        if (sucursal != null) {
            List<Cuenta> cuentas = sucursal.getCuentas();
            return cuentas.stream()
                    .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public List<Object> obtenerClientesPorSucursalId(Long sucursalId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);
        if (sucursal != null) {
            List<Cliente> clientes = sucursal.getClientes();
            return clientes.stream()
                    .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public SucursalDTO asociarCuentaASucursal(Long sucursalId, Long cuentaId) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);

        if (sucursal != null && cuenta != null) {
            sucursal.agregarCuenta(cuenta);
            sucursal = sucursalRepository.save(sucursal);
            return modelMapper.map(sucursal, SucursalDTO.class);
        } else {
            return null;
        }
    }

    public ClienteDTO crearClienteConCuenta(Long sucursalId, ClienteDTO clienteDTO) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId).orElse(null);

        if (sucursal != null) {
            // Crear un nuevo cliente y configurar sus propiedades
            Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
            cliente.setTipoCuenta(TipoCuenta.AHORROS); // Puedes ajustar el tipo de cuenta según sea necesario
            cliente.setSucursal(sucursal); // Asociar el cliente con la sucursal

            // Guardar el nuevo cliente en la base de datos
            cliente = clienteRepository.save(cliente);

            return modelMapper.map(cliente, ClienteDTO.class);
        } else {
            return null;
        }
    }
}
