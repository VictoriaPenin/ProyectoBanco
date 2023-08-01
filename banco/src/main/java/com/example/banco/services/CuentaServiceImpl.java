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
import java.util.stream.Collectors;

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
        return cuentas.stream()
                .map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO findCuentaById(Long id) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        return optionalCuenta.map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class)).orElse(null);
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }


   /* @Override
    public CuentaDTO transferirDinero(Long idCuentaOrigen, Long idCuentaDestino, double monto) {
        // Recupera ambas cuentas (la de origen y la de destino) desde el repositorio
        Cuenta cuentaOrigen = cuentaRepository.findById(idCuentaOrigen)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada"));
        Cuenta cuentaDestino = cuentaRepository.findById(idCuentaDestino)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada"));

        // Verifica si la cuenta de origen tiene suficiente saldo para realizar la transferencia
        if (cuentaOrigen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta de origen");
        }

        // Realiza la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        // Guarda las cuentas actualizadas en el repositorio
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        return modelMapper.map(cuentaOrigen, CuentaDTO.class);
    }

    @Override
    public CuentaDTO depositarDinero(Long idCuenta, double monto) {
        // Recupera la cuenta desde el repositorio
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        // Realiza el depósito
        cuenta.setSaldo(cuenta.getSaldo() + monto);

        // Guarda la cuenta actualizada en el repositorio
        cuentaRepository.save(cuenta);

        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO retirarDinero(Long idCuenta, double monto) {
        // Recupera la cuenta desde el repositorio
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        // Verifica si la cuenta tiene suficiente saldo para realizar el retiro
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta");
        }

        // Realiza el retiro
        cuenta.setSaldo(cuenta.getSaldo() - monto);

        // Guarda la cuenta actualizada en el repositorio
        cuentaRepository.save(cuenta);

        return modelMapper.map(cuenta, CuentaDTO.class);
    }
*/


    @Override
    public CuentaDTO transferirDinero(Long idCuentaOrigen, Long idCuentaDestino, double monto) {
        // Obtener las cuentas desde el repositorio
        Cuenta cuentaOrigen = cuentaRepository.findById(idCuentaOrigen)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada"));
        Cuenta cuentaDestino = cuentaRepository.findById(idCuentaDestino)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada"));

        // Realizar validaciones y operaciones necesarias
        if (cuentaOrigen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta de origen");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        // Crear y registrar las transacciones
        Transaccion transaccionOrigen = new Transaccion();
        transaccionOrigen.setTipoTransaccion(TipoTransaccion.TRANSFERENCIA);
        transaccionOrigen.setCuenta(cuentaOrigen);
        transaccionOrigen.setMonto(-monto);
        transaccionRepository.save(transaccionOrigen);

        Transaccion transaccionDestino = new Transaccion();
        transaccionDestino.setTipoTransaccion(TipoTransaccion.TRANSFERENCIA);
        transaccionDestino.setCuenta(cuentaDestino);
        transaccionDestino.setMonto(monto);
        transaccionRepository.save(transaccionDestino);

        // Guardar las cuentas actualizadas en el repositorio
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        // Convertir las entidades a DTOs
        CuentaDTO cuentaOrigenDTO = modelMapper.map(cuentaOrigen, CuentaDTO.class);
        CuentaDTO cuentaDestinoDTO = modelMapper.map(cuentaDestino, CuentaDTO.class);

        // Realizar otras operaciones, si es necesario

        // Retornar los DTOs
        return cuentaOrigenDTO;
    }

    @Override
    public CuentaDTO depositarDinero(Long idCuenta, double monto) {
        // Obtener la cuenta desde el repositorio
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        // Realizar validaciones y operaciones necesarias
        cuenta.setSaldo(cuenta.getSaldo() + monto);

        // Crear y registrar la transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(TipoTransaccion.DEPOSITO);
        transaccion.setCuenta(cuenta);
        transaccion.setMonto(monto);
        transaccionRepository.save(transaccion);

        // Guardar la cuenta actualizada en el repositorio
        cuentaRepository.save(cuenta);

        // Convertir la entidad a DTO

        // Realizar otras operaciones, si es necesario

        // Retornar el DTO
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO retirarDinero(Long idCuenta, double monto) {
        // Obtener la cuenta desde el repositorio
        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        // Realizar validaciones y operaciones necesarias
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta");
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);

        // Crear y registrar la transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(TipoTransaccion.EXTRACCION);
        transaccion.setCuenta(cuenta);
        transaccion.setMonto(-monto);
        transaccionRepository.save(transaccion);

        // Guardar la cuenta actualizada en el repositorio
        cuentaRepository.save(cuenta);

        // Convertir la entidad a DTO

        // Realizar otras operaciones, si es necesario

        // Retornar el DTO
        return modelMapper.map(cuenta, CuentaDTO.class);
    }
}

