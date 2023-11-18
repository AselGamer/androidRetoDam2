package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Articulo2 implements Serializable {
    @SerializedName("idarticulo")
    int idarticulo;
    @SerializedName("nombre")
    String nombre;
    @SerializedName("precio")
    float precio;
    @SerializedName("stock")
    int stock;
    @SerializedName("foto")
    String foto;
    @SerializedName("idmarca")
    Marca idmarca;
    @SerializedName("stockAlquiler")
    int stockAlquiler;

    public Articulo2(int idarticulo, String nombre, float precio, int stock, String foto, Marca idmarca, int stockAlquiler) {
        this.idarticulo = idarticulo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.foto = foto;
        this.idmarca = idmarca;
        this.stockAlquiler = stockAlquiler;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getStockAlquiler() {
        return stockAlquiler;
    }

    public void setStockAlquiler(int stockAlquiler) {
        this.stockAlquiler = stockAlquiler;
    }
}
