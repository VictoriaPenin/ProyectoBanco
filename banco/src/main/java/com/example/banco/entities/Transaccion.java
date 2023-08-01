package com.example.banco.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private double monto;
    private LocalDate fecha;

    public Transaccion() {
    }

    public Transaccion(Long id, TipoTransaccion tipoTransaccion, Cuenta cuenta, Cliente cliente, double monto, LocalDate fecha) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.monto = monto;
        this.fecha = fecha;
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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", tipoTransaccion=" + tipoTransaccion +
                ", cuenta=" + cuenta +
                ", cliente=" + cliente +
                ", monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }
}