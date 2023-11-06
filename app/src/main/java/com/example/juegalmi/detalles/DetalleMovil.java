package com.example.juegalmi.detalles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juegalmi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleMovil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleMovil extends Fragment {

    public DetalleMovil() {
        // Required empty public constructor
    }

    public static DetalleMovil newInstance(String param1, String param2) {
        DetalleMovil fragment = new DetalleMovil();
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
        return inflater.inflate(R.layout.fragment_detalle_movil, container, false);
    }
}