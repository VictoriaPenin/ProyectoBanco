package com.example.banco.DTO;


import com.example.banco.entities.Cuenta;
import com.example.banco.entities.TipoCuenta;
import jakarta.persistence.OneToMany;
import java.util.List;

public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String telefono;
    private String dni;
    private String email;
    private TipoCuenta tipoCuenta;
    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombre, String apellido, String domicilio, String telefono, String dni, String email, TipoCuenta tipoCuenta, List<Cuenta> cuentas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
        this.tipoCuenta = tipoCuenta;
        this.cuentas = cuentas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}