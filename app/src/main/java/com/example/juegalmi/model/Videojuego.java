package com.example.juegalmi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Videojuego implements Serializable {
    @SerializedName("idvideojuego")
    int idvideojuego;
    @SerializedName("idplataforma")
    Plataforma idplataforma;
    @SerializedName("idarticulo")
    Articulo idarticulo;
    @SerializedName("etiquetas")
    Etiqueta[] etiquetas;

    public Videojuego(int idvideojuego, Plataforma idplataforma, Articulo idarticulo, Etiqueta[] etiquetas) {
        //super(idarticulo);
        this.idvideojuego = idvideojuego;
        this.idplataforma = idplataforma;
        this.idarticulo = idarticulo;
        this.etiquetas = etiquetas;
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
}
