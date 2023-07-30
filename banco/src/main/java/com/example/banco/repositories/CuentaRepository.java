package com.example.banco.repositories;

import com.example.banco.entities.Cliente;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByCliente(Cliente cliente);

    List<Cuenta> findBySucursal(Sucursal sucursal);
}
