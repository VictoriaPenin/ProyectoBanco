package com.example.banco.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String domicilio;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas;
    //si se elimina sucursal, se eliminan las entidades relacionadas
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    public Sucursal() {
    }

    public Sucursal(Long id, String nombre, String domicilio, List<Cuenta> cuentas, List<Cliente> clientes) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.cuentas = cuentas;
        this.clientes = clientes;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", cuentas=" + cuentas +
                ", clientes=" + clientes +
                '}';
    }

    public void agregarCuenta(Cuenta cuenta) {
    }
}