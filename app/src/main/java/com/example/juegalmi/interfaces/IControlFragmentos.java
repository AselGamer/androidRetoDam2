package com.example.juegalmi.interfaces;

import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;
import com.example.juegalmi.model.Usuario;

import java.util.ArrayList;

public interface IControlFragmentos {
    public void cambiarTitulo(String titulo);
    public void cambiarSesion(Usuario usuario);
    public Usuario obtenerSesion();
    public ArrayList<Articulo> obtenerListaArticulos();
    public ArrayList<Etiqueta> obtenerListaEtiquetas();
}
