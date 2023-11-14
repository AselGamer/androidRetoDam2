package com.example.juegalmi.tabsArriba;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Todo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Todo extends Fragment {

    private RecyclerView recycler1, recycler2, recycler3;
    private ArrayList<Articulo> listaArticulos = new ArrayList<>();
    private ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
    private ImageButton imgMas;
    private RecyclerAdaptador adaptador;
    private int suma = 0;
    private IControlFragmentos activity;
    private TextView txtSubtitulo;

    public Todo() {
        // Required empty public constructor
    }

    public static Todo newInstance(String param1, String param2) {
        Todo fragment = new Todo();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rellenarArticulos();

        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_todo, container, false);

        txtSubtitulo = vista.findViewById(R.id.txtSubtitulo);

        recycler1 = vista.findViewById(R.id.recycler1);
        recycler1.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaArticulos);
        recycler1.setAdapter(adaptador);

        recycler2 = vista.findViewById(R.id.recycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaArticulos);
        recycler2.setAdapter(adaptador);

        recycler3 = vista.findViewById(R.id.recycler3);
        recycler3.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador = new RecyclerAdaptador(vista.getContext(), listaArticulos);
        recycler3.setAdapter(adaptador);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //txtSubtitulo.setText(listaEtiquetas.get);

        recycler1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaArticulos.size()-4)*319 ){  //Si llega al final del scroll
                    recycler1.scrollToPosition(0);
                    suma = 0;
                }
            }
        });

        recycler2.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaArticulos.size()-4)*319 ){  //Si llega al final del scroll
                    recycler2.scrollToPosition(0);
                    suma = 0;
                }
            }
        });

        recycler3.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaArticulos.size()-4)*319 ){  //Si llega al final del scroll
                    recycler3.scrollToPosition(0);
                    suma = 0;
                }
            }
        });
    }

    private void rellenarArticulos() {
        listaArticulos = activity.obtenerListaArticulos();
        Log.d("lista", listaArticulos.toString());
        listaEtiquetas = activity.obtenerListaEtiquetas();
        //listaEtiquetas =
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