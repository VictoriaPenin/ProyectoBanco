package com.example.banco.DTO;

import com.example.banco.entities.Cliente;
import com.example.banco.entities.TipoTransaccion;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class TransaccionDTO {
    private Long id;
    private TipoTransaccion tipoTransaccion;
    private Long idCuentaOrigen; // Nuevo campo para el ID de la cuenta de origen
    private Long idCuentaDestino; // Nuevo campo para el ID de la cuenta de destino (solo para transferencias)
    private double monto;
    private Cliente cliente;

    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaDTO cuenta;

    public TransaccionDTO() {
    }

    public TransaccionDTO(Long id, TipoTransaccion tipoTransaccion, Long idCuentaOrigen, Long idCuentaDestino, double monto, Cliente cliente, LocalDate fecha, CuentaDTO cuenta) {
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

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}