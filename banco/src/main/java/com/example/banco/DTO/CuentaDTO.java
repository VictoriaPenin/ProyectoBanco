package com.example.banco.DTO;

import com.example.banco.entities.Cliente;
import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Sucursal;
import com.example.banco.entities.Transaccion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuentaDTO {
    private Long id;
    private String tipoCuenta;
    private double saldo;
    private Boolean estado;
    private LocalDate fechaAlta;


    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Transaccion> transacciones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

    public CuentaDTO() {
    }

    public CuentaDTO(Long id, String tipoCuenta, double saldo, Boolean estado, LocalDate fechaAlta, Sucursal sucursal, Cliente cliente, List<Transaccion> transacciones, List<Cuenta> cuentas) {
        this.id = id;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.transacciones = transacciones;
        this.cuentas = cuentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" +
                "id=" + id +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldo=" + saldo +
                ", estado=" + estado +
                ", fechaAlta=" + fechaAlta +
                ", sucursal=" + sucursal +
                ", cliente=" + cliente +
                ", transacciones=" + transacciones +
                ", cuentas=" + cuentas +
                '}';
    }
}