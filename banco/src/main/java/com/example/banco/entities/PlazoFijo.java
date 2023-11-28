package com.example.banco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class PlazoFijo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private Long idCliente;
    private Long idSucursal;
    private int plazoDias;
    private double monto;
    private double interes;

    public PlazoFijo() {
    }

    public PlazoFijo(Long id, LocalDate fecha, Long idCliente, Long idSucursal, int plazoDias, double monto, double interes) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.plazoDias = plazoDias;
        this.monto = monto;
        this.interes = interes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int plazoDias) {
        this.plazoDias = plazoDias;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    @Override
    public String toString() {
        return "PlazoFijo{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", idCliente=" + idCliente +
                ", idSucursal=" + idSucursal +
                ", plazoDias=" + plazoDias +
                ", monto=" + monto +
                ", interes=" + interes +
                '}';
    }
}