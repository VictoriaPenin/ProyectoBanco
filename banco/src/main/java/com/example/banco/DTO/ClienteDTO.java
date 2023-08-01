package com.example.banco.DTO;

import com.example.banco.entities.Cuenta;
import com.example.banco.entities.Sucursal;
import com.example.banco.entities.TipoCuenta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ClienteDTO {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")

    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")

    private String apellido;
    private String domicilio;
    private String telefono;
    @NotBlank(message = "El DNI no puede ser nulo")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    private String dni;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    private TipoCuenta tipoCuenta;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombre, String apellido, String domicilio, String telefono, String dni, String email, List<Cuenta> cuentas, Sucursal sucursal, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
        this.cuentas = cuentas;
        this.sucursal = sucursal;
        this.tipoCuenta = tipoCuenta;
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

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", cuentas=" + cuentas +
                ", sucursal=" + sucursal +
                ", tipoCuenta=" + tipoCuenta +
                '}';
    }
}