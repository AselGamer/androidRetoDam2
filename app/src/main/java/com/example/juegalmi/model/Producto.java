package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Producto implements Serializable {
    @SerializedName("idarticulo")
    private Articulo idarticulo;
    @SerializedName("idvideojuego")
    private int idvideojuego;
    @SerializedName("idplataforma")
    private Plataforma idplataforma;
    @SerializedName("etiquetas")
    private Etiqueta[] etiquetas;
    @SerializedName("idconsola")
    private int idconsola;
    @SerializedName("modelo")
    private String modelo;
    @SerializedName("cantmandos")
    private String cantmandos;
    @SerializedName("plataformas")
    private Plataforma[] plataformas;
    @SerializedName("iddispositivomovil")
    private int iddispositivomovil;
    @SerializedName("almacenamiento")
    private String almacenamiento;
    @SerializedName("ram")
    private String ram;
    @SerializedName("tamanoPantalla")
    private String tamanoPantalla;

    public Producto(Articulo idarticulo, int idvideojuego, Plataforma idplataforma, Etiqueta[] etiquetas, int idconsola, String modelo, String cantmandos, Plataforma[] plataformas, int iddispositivomovil, String almacenamiento, String ram, String tamanoPantalla) {
        this.idarticulo = idarticulo;
        this.idvideojuego = idvideojuego;
        this.idplataforma = idplataforma;
        this.etiquetas = etiquetas;
        this.idconsola = idconsola;
        this.modelo = modelo;
        this.cantmandos = cantmandos;
        this.plataformas = plataformas;
        this.iddispositivomovil = iddispositivomovil;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.tamanoPantalla = tamanoPantalla;
    }

    public Articulo getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Articulo idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getIdvideojuego() {
        return idvideojuego;
    }

    public void setIdvideojuego(int idvideojuego) {
        this.idvideojuego = idvideojuego;
    }

    public Plataforma getIdplataforma() {
        return idplataforma;
    }

    public void setIdplataforma(Plataforma idplataforma) {
        this.idplataforma = idplataforma;
    }

    public Etiqueta[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Etiqueta[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public int getIdconsola() {
        return idconsola;
    }

    public void setIdconsola(int idconsola) {
        this.idconsola = idconsola;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCantmandos() {
        return cantmandos;
    }

    public void setCantmandos(String cantmandos) {
        this.cantmandos = cantmandos;
    }

    public Plataforma[] getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(Plataforma[] plataformas) {
        this.plataformas = plataformas;
    }

    public int getIddispositivomovil() {
        return iddispositivomovil;
    }

    public void setIddispositivomovil(int iddispositivomovil) {
        this.iddispositivomovil = iddispositivomovil;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getTamanoPantalla() {
        return tamanoPantalla;
    }

    public void setTamanoPantalla(String tamanoPantalla) {
        this.tamanoPantalla = tamanoPantalla;
    }
}
