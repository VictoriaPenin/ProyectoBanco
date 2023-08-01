package com.example.banco.DTO;

import com.example.banco.entities.Cuenta;
import com.example.banco.entities.TipoTransaccion;

public class TransaccionDTO {
    private Long id;
    private TipoTransaccion tipoTransaccion;
    private Long idCuenta;
    private double monto;

    // Constructor, getters y setters

    public TransaccionDTO() {
    }

    public TransaccionDTO(Long id, TipoTransaccion tipoTransaccion, Long idCuenta, double monto) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.idCuenta = idCuenta;
        this.monto = monto;
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

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setCuenta(Cuenta map) {
    }
}
