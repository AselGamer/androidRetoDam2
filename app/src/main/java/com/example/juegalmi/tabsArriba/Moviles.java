package com.example.juegalmi.tabsArriba;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.adaptadores.TipoAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Moviles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Moviles extends Fragment {

    private IControlFragmentos activity;
    private ListView listaTipos = null;

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

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_moviles, container, false);

        Call<Map<String, List<Articulo>>> call = ApiAdaptador.getApiService().getAllByType();/////////////////Cambiar
        call.enqueue(new Callback<Map<String, List<Articulo>>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Map<String, List<Articulo>>> call, Response<Map<String, List<Articulo>>> response) {
                if(response.isSuccessful()){
                    HashMap<String, List<Articulo>> lr = new HashMap<>(response.body());

                    TipoAdaptador tipoAdaptador = new TipoAdaptador(vista.getContext(), lr);

                    listaTipos = vista.findViewById(R.id.listTipos);
                    listaTipos.setAdapter(tipoAdaptador);
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, List<Articulo>>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return vista;

        /*recycler1 = vista.findViewById(R.id.recycler1);
        recycler1.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaImagenes, false);
        recycler1.setAdapter(adaptador);

        recycler2 = vista.findViewById(R.id.recycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaImagenes, false);
        recycler2.setAdapter(adaptador);

        recycler3 = vista.findViewById(R.id.recycler3);
        recycler3.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaImagenes, false);
        recycler3.setAdapter(adaptador);

        return vista;*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        /*recycler1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaImagenes.size()-4)*319 ){  //Si llega al final del scroll
                    recycler1.scrollToPosition(0);
                    suma = 0;
                }
            }
        });

        recycler2.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaImagenes.size()-4)*319 ){  //Si llega al final del scroll
                    recycler2.scrollToPosition(0);
                    suma = 0;
                }
            }
        });

        recycler3.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaImagenes.size()-4)*319 ){  //Si llega al final del scroll
                    recycler3.scrollToPosition(0);
                    suma = 0;
                }
            }
        });*/
    }

    private void rellenarFotos() {
        /*listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));
        listaImagenes.add(new Imagen("https://media.game.es/COVERV2/3D_L/130/130519.png", "Dark Souls", "10€", "Infinity"));*/
        /*listaImagenes.add(new Imagen("https://cdn-icons-png.flaticon.com/512/992/992651.png", "Ver Todo", "", ""));
        listaImagenes.add(new Imagen("https://cdn-icons-png.flaticon.com/512/992/992651.png", "Ver Todo", "", ""));*/
    }
}