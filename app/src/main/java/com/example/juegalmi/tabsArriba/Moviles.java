package com.example.juegalmi.tabsArriba;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.model.Imagen;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Moviles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Moviles extends Fragment {

    private RecyclerView recycler1, recycler2, recycler3;
    ArrayList<Imagen> listaImagenes = new ArrayList<>();
    ImageButton imgMas;
    RecyclerAdaptador adaptador;

    public Moviles() {
        // Required empty public constructor
    }

    public static Moviles newInstance(String param1, String param2) {
        Moviles fragment = new Moviles();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rellenarFotos();

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_videojuegos, container, false);

        recycler1 = vista.findViewById(R.id.recycler1);
        recycler1.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaImagenes);
        recycler1.setAdapter(adaptador);

        recycler2 = vista.findViewById(R.id.recycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaImagenes);
        recycler2.setAdapter(adaptador);

        recycler3 = vista.findViewById(R.id.recycler3);
        recycler3.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaImagenes);
        recycler3.setAdapter(adaptador);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void rellenarFotos() {
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://cdn-icons-png.flaticon.com/512/992/992651.png", "Ver Todo", "", ""));
        listaImagenes.add(new Imagen("https://cdn-icons-png.flaticon.com/512/992/992651.png", "Ver Todo", "", ""));
    }
}