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

    //FALTA CAMBIAR LA BBDD
}
