package com.example.juegalmi.interfaces;

import androidx.fragment.app.Fragment;

import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;
import com.example.juegalmi.model.Usuario;

import java.util.ArrayList;

public interface IControlFragmentos {
    public void cambiarTitulo(String titulo);
    public void cambiarSesion(Usuario usuario);
    public Usuario obtenerSesion();
    public void cambiarToken(String token);
    public String obtenerToken();
    public ArrayList<Articulo> obtenerListaArticulos();
    public ArrayList<Etiqueta> obtenerListaEtiquetas();
    public ArrayList<Articulo> obtenerListaArticulosCesta();
    public ArrayList<Integer> obtenerListaCantidad();
    public void cambiarListaArticulosCesta(Articulo articulo);
    public void quitarListaArticulosCesta(int posicion);
    public void cambiarListaCantidad(ArrayList<Integer> listaCantidad);
    public void quitarListaCantidad(int posicion);
    public void cambiarFragmento(Fragment fragmento);
}
