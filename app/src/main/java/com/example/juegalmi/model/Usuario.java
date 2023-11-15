package com.example.juegalmi.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idusuario = -1;
    private String nombre = "";
    private String apellido1 = "";
    private String apellido2 = "";
    private String email = "";
    private String telefono = "";
    private String calle = "";
    private String numPortal = "";
    private String piso = "";
    private String codigoPostal = "";
    private String ciudad = "";
    private String pais = "";
    private String provincia = "";

    public Usuario(int idusuario, String nombre, String apellido1, String apellido2, String email, String telefono, String calle, String numPortal, String piso, String codigoPostal, String ciudad, String pais, String provincia) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.calle = calle;
        this.numPortal = numPortal;
        this.piso = piso;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.pais = pais;
        this.provincia = provincia;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumPortal() {
        return numPortal;
    }

    public void setNumPortal(String numPortal) {
        this.numPortal = numPortal;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
