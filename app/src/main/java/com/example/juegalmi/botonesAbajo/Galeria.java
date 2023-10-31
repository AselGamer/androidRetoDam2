package com.example.juegalmi.botonesAbajo;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.adaptadores.RecyclerAdaptadorGaleria;
import com.example.juegalmi.model.FotoGaleria;
import com.example.juegalmi.model.Imagen;

import java.util.ArrayList;

public class Galeria extends Fragment {

    private RecyclerView recyclerView;
    TypedArray fotos;
    String[] textos;
    RecyclerAdaptadorGaleria adaptador;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_galeria, container, false);
        rellenarFotos();
        recyclerView = vista.findViewById(R.id.miRecyclerGaleria);
        recyclerView.setLayoutManager(new GridLayoutManager(vista.getContext(), 2));    //numero de columnas
        adaptador = new RecyclerAdaptadorGaleria(vista.getContext(), fotos, textos);
        recyclerView.setAdapter(adaptador);

        return vista;
    }

    private void rellenarFotos() {
        fotos = getResources().obtainTypedArray(R.array.fotos);
        textos = getResources().getStringArray(R.array.fotosDescripcion);
    }
}