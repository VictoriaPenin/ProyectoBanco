package com.example.banco.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoTransaccion tipoTransaccion;
    private Long idCuentaOrigen;
    private Long idCuentaDestino;
    private double monto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    public Transaccion() {
    }

    public Transaccion(Long id, TipoTransaccion tipoTransaccion, Long idCuentaOrigen, Long idCuentaDestino, double monto, Cliente cliente, LocalDate fecha, Cuenta cuenta) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.monto = monto;
        this.cliente = cliente;
        this.fecha = fecha;
        this.cuenta = cuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Long getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(Long idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public Long getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(Long idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", tipoTransaccion=" + tipoTransaccion +
                ", idCuentaOrigen=" + idCuentaOrigen +
                ", idCuentaDestino=" + idCuentaDestino +
                ", monto=" + monto +
                ", cliente=" + cliente +
                ", fecha=" + fecha +
                ", cuenta=" + cuenta +
                '}';
    }
}