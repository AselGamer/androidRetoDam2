package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Articulo implements Serializable {
    @SerializedName("idarticulo")
    int idarticulo;
    @SerializedName("articulonombre")
    String articulonombre;
    @SerializedName("nombre")
    String nombre;
    @SerializedName("tipoarticulo")
    String tipoarticulo;
    @SerializedName("precio")
    float precio;
    @SerializedName("stock")
    int stock;
    @SerializedName("foto")
    String foto;
    @SerializedName("idmarca")
    Marca idmarca;
    @SerializedName("idtipoClase")
    int idtipoClase;
    @SerializedName("stockAlquiler")
    int stockAlquiler;

    public Articulo(int idarticulo, String articulonombre, String nombre, String tipoarticulo, float precio, int stock, String foto, Marca idmarca, int idtipoClase, int stockAlquiler) {
        this.idarticulo = idarticulo;
        this.articulonombre = articulonombre;
        this.nombre = nombre;
        this.tipoarticulo = tipoarticulo;
        this.precio = precio;
        this.stock = stock;
        this.foto = foto;
        this.idmarca = idmarca;
        this.idtipoClase = idtipoClase;
        this.stockAlquiler = stockAlquiler;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getArticulonombre() {
        return articulonombre;
    }

    public void setArticulonombre(String articulonombre) {
        this.articulonombre = articulonombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoarticulo() {
        return tipoarticulo;
    }

    public void setTipoarticulo(String tipoarticulo) {
        this.tipoarticulo = tipoarticulo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Marca getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Marca idmarca) {
        this.idmarca = idmarca;
    }

    public int getIdtipoClase() {
        return idtipoClase;
    }

    public void setIdtipoClase(int idtipoClase) {
        this.idtipoClase = idtipoClase;
    }

    public int getStockAlquiler() {
        return stockAlquiler;
    }

    public void setStockAlquiler(int stockAlquiler) {
        this.stockAlquiler = stockAlquiler;
    }
}
