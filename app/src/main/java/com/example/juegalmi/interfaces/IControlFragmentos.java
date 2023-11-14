package com.example.juegalmi.interfaces;

import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;

import java.util.ArrayList;

public interface IControlFragmentos {
    public void cambiarTitulo(String titulo);
    public void cambiarSesion(String sesion);
    public String obtenerSesion();
    public ArrayList<Articulo> obtenerListaArticulos();
    public ArrayList<Etiqueta> obtenerListaEtiquetas();
    public void cambiarFocus();
}
