package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Producto implements Serializable {
    @SerializedName("idarticulo")
    Articulo idarticulo;

    public Producto(Articulo idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Articulo getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Articulo idarticulo) {
        this.idarticulo = idarticulo;
    }
}
