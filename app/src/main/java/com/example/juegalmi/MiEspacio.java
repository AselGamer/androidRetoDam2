package com.example.juegalmi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.interfaces.IControlFragmentos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiEspacio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiEspacio extends Fragment {

    private Button btnCerrar;
    private LinearLayout layDatos, layPedidos, layAlquileres, layReparaciones;
    private IControlFragmentos activity;

    public MiEspacio() {
        // Required empty public constructor
    }

    public static MiEspacio newInstance(String param1, String param2) {
        MiEspacio fragment = new MiEspacio();
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

    //Metodo para interactuar con la actividad principal
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    //Es el metodo para cargar el layout asociado al fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mi_espacio, container, false);

        btnCerrar = vista.findViewById(R.id.btnCerrar);
        layDatos = vista.findViewById(R.id.layDatos);
        layPedidos = vista.findViewById(R.id.layPedidos);
        layAlquileres = vista.findViewById(R.id.layAlquileres);
        layReparaciones = vista.findViewById(R.id.layReparaciones);

        return vista;
    }

    //Se ejecuta al cargar el fragmento
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarSesion(null);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new Productos())
                        .commit();
            }
        });

        layDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Datos Personales");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new DatosPersonales())
                        .commit();
            }
        });

        layPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Mis Pedidos");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new MisPedidos())
                        .commit();
            }
        });

        layAlquileres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Mis Alquileres");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new MisAlquileres())
                        .commit();
            }
        });

        layReparaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Mis Reparaciones");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new MisReparaciones())
                        .commit();
            }
        });
    }
}