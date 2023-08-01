package com.example.banco.DTO;

import com.example.banco.entities.Cliente;
import com.example.banco.entities.Sucursal;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CuentaDTO {
    private Long id;
    @NotBlank(message = "El tipo de cuenta no puede ser nulo")
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


    public CuentaDTO() {
    }

    public CuentaDTO(Long id, String tipoCuenta, double saldo, Boolean estado, LocalDate fechaAlta, Sucursal sucursal, Cliente cliente) {
        this.id = id;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.sucursal = sucursal;
        this.cliente = cliente;
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
                '}';
    }

}
