package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

public class Articulo {
    @SerializedName("idarticulo")
    String idarticulo;
    @SerializedName("articulonombre")
    String articulonombre;
    @SerializedName("tipoarticulo")
    String tipoarticulo;
    @SerializedName("precio")
    float precio;
    @SerializedName("stock")
    int stock;
    @SerializedName("idmarca")
    Marca idmarca;
    @SerializedName("idtipoClase")
    int idtipoClase;

    public Articulo(String idarticulo, String articulonombre, String tipoarticulo, float precio, int stock, Marca idmarca, int idtipoClase) {
        this.idarticulo = idarticulo;
        this.articulonombre = articulonombre;
        this.tipoarticulo = tipoarticulo;
        this.precio = precio;
        this.stock = stock;
        this.idmarca = idmarca;
        this.idtipoClase = idtipoClase;
    }

    public String getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(String idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getArticulonombre() {
        return articulonombre;
    }

    public void setArticulonombre(String artiulonombre) {
        this.articulonombre = artiulonombre;
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
}
