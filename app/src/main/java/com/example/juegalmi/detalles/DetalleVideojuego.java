package com.example.juegalmi.detalles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juegalmi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleVideojuego#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleVideojuego extends Fragment {

    public DetalleVideojuego() {
        // Required empty public constructor
    }

    public static DetalleVideojuego newInstance(String param1, String param2) {
        DetalleVideojuego fragment = new DetalleVideojuego();
        Bundle args = new Bundle();

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_videojuego, container, false);
    }
}