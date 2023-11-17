package com.example.juegalmi.detalles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juegalmi.R;
import com.example.juegalmi.model.Articulo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleVideojuego#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleVideojuego extends Fragment {
    private TextView txtTitulo, txtPlataforma, txtCategoria, txtPrecioAlquiler, txtPrecioCompra;

    public DetalleVideojuego() {
        // Required empty public constructor
    }

    public static DetalleVideojuego newInstance(Bundle args) {
        DetalleVideojuego fragment = new DetalleVideojuego();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detalle_videojuego, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        txtTitulo = vista.findViewById(R.id.txtTitulo);
        txtPlataforma = vista.findViewById(R.id.txtPlataforma);
        txtCategoria = vista.findViewById(R.id.txtCategoria);
        txtPrecioAlquiler = vista.findViewById(R.id.txtPrecioAlquiler);
        txtPrecioCompra = vista.findViewById(R.id.txtPrecioCompra);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*if(getArguments().containsKey("texto1"))
        {
            txtTitulo.setText(getArguments().getString("texto1"));
        }
        if(getArguments().containsKey("texto2"))
        {
            txtTitulo.setText(getArguments().getString("texto2"));
        }
        if(getArguments().containsKey("texto3"))
        {
            txtTitulo.setText(getArguments().getString("texto3"));
        }*/
        if(getArguments().containsKey("articulo"))
        {
            /*FALTA CAMBIAR LA BBDD
            Articulo articulo = (Articulo) getArguments().getSerializable("articulo");
            txtTitulo.setText(articulo.getArticulonombre());
            txtPlataforma.setText(articulo.getId());
            txtCategoria.setText(articulo.getArticulonombre());
            txtPrecioAlquiler.setText(articulo.getArticulonombre());
            txtPrecioCompra.setText(articulo.getArticulonombre());
            */
        }
    }
}