package com.example.juegalmi.tabsArriba;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Todo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Todo extends Fragment {

    private RecyclerView recycler1, recycler2, recycler3;
    private ArrayList<Articulo> listaVideojuegos, listaConsolas, listaMoviles;
    private ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
    private ImageButton imgMas;
    private RecyclerAdaptador adaptador1, adaptador2, adaptador3;
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

        listaVideojuegos = new ArrayList<>();
        listaConsolas = new ArrayList<>();
        listaMoviles = new ArrayList<>();

        //rellenarArticulos();

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
        adaptador1 = new RecyclerAdaptador(vista.getContext(), listaVideojuegos, false);
        recycler1.setAdapter(adaptador1);

        recycler2 = vista.findViewById(R.id.recycler2);
        recycler2.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador2 = new RecyclerAdaptador(vista.getContext(), listaConsolas, false);
        recycler2.setAdapter(adaptador2);

        recycler3 = vista.findViewById(R.id.recycler3);
        recycler3.setLayoutManager(new LinearLayoutManager(vista.getContext(), RecyclerView.HORIZONTAL, false));
        adaptador3 = new RecyclerAdaptador(vista.getContext(), listaMoviles, false);
        recycler3.setAdapter(adaptador3);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //txtSubtitulo.setText(listaEtiquetas.get);

        recycler1.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaVideojuegos.size()-4)*319 ){  //Si llega al final del scroll
                    recycler1.scrollToPosition(0);
                    suma = 0;
                }
            }
        });

        recycler2.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaConsolas.size()-4)*319 ){  //Si llega al final del scroll
                    recycler2.scrollToPosition(0);
                    suma = 0;
                }
            }
        });

        recycler3.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int x, int y, int oldX, int oldY) {
                suma = suma + oldX;
                if( suma <= -196-(listaMoviles.size()-4)*319 ){  //Si llega al final del scroll
                    recycler3.scrollToPosition(0);
                    suma = 0;
                }
            }
        });
    }

    /*private void rellenarArticulos() {
        listaArticulos = activity.obtenerListaArticulos();
        Log.d("lista", listaArticulos.toString());
        listaEtiquetas = activity.obtenerListaEtiquetas();
        //listaEtiquetas =*/
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
   //}

    private void rellenarArticulos() {
        //Obtener todos los articulos
        /*Call<List<Articulo>> call = ApiAdaptador.getApiService().getArticulos();
        call.enqueue(new Callback<List<Articulo>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                if(response.isSuccessful()){
                    List<Articulo> lr = response.body();
                    for(int i=0; i<lr.size(); i++){
                        listaArticulos.add(new Articulo(lr.get(i).getIdarticulo(), lr.get(i).getArticulonombre(),
                                lr.get(i).getTipoarticulo(), lr.get(i).getPrecio(), lr.get(i).getStock(),
                                lr.get(i).getFoto(), lr.get(i).getIdmarca(), lr.get(i).getIdtipoClase()));
                    }
                    Toast.makeText(getContext(), "Articulos cargados", Toast.LENGTH_SHORT).show();
                    adaptador1.setListaArticulos(listaArticulos);
                    adaptador1.notifyDataSetChanged();
                    adaptador2.setListaArticulos(listaArticulos);
                    adaptador2.notifyDataSetChanged();
                    adaptador3.setListaArticulos(listaArticulos);
                    adaptador3.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });*/

        //Obtener todos los articulos segun el tipoarticulo
        /*Call<List<Articulo>> callVideojuegos = ApiAdaptador.getApiService().getArticulos("Videojuego");
        callVideojuegos.enqueue(new Callback<List<Articulo>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Articulo>> callVideojuegos, Response<List<Articulo>> response) {
                if(response.isSuccessful()){
                    List<Articulo> lr = response.body();
                    for(int i=0; i<lr.size(); i++){
                        listaVideojuegos.add(new Articulo(lr.get(i).getIdarticulo(), lr.get(i).getArticulonombre(),
                                lr.get(i).getTipoarticulo(), lr.get(i).getPrecio(), lr.get(i).getStock(),
                                lr.get(i).getFoto(), lr.get(i).getIdmarca(), lr.get(i).getIdtipoClase()));
                    }
                    adaptador1.setListaArticulos(listaVideojuegos);
                    adaptador1.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> callVideojuegos, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Articulo>> callConsolas = ApiAdaptador.getApiService().getArticulos("Consola");
        callConsolas.enqueue(new Callback<List<Articulo>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Articulo>> callConsolas, Response<List<Articulo>> response) {
                if(response.isSuccessful()){
                    List<Articulo> lr = response.body();
                    for(int i=0; i<lr.size(); i++){
                        listaConsolas.add(new Articulo(lr.get(i).getIdarticulo(), lr.get(i).getArticulonombre(),
                                lr.get(i).getTipoarticulo(), lr.get(i).getPrecio(), lr.get(i).getStock(),
                                lr.get(i).getFoto(), lr.get(i).getIdmarca(), lr.get(i).getIdtipoClase()));
                    }
                    adaptador2.setListaArticulos(listaConsolas);
                    adaptador2.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> callConsolas, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Articulo>> callMoviles = ApiAdaptador.getApiService().getArticulos("DispositivoMovil");
        callMoviles.enqueue(new Callback<List<Articulo>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Articulo>> callMoviles, Response<List<Articulo>> response) {
                if(response.isSuccessful()){
                    List<Articulo> lr = response.body();
                    for(int i=0; i<lr.size(); i++){
                        listaMoviles.add(new Articulo(lr.get(i).getIdarticulo(), lr.get(i).getArticulonombre(),
                                lr.get(i).getTipoarticulo(), lr.get(i).getPrecio(), lr.get(i).getStock(),
                                lr.get(i).getFoto(), lr.get(i).getIdmarca(), lr.get(i).getIdtipoClase()));
                    }
                    adaptador3.setListaArticulos(listaMoviles);
                    adaptador3.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "No se han podido cargar los articulos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> callMoviles, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
*/

        Call<Object> datos = ApiAdaptador.getApiService().getAllByType();

        datos.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> datos, Response<Object> response)
            {
                if (response.isSuccessful())
                {
                    Log.d("lista", response.body().toString());
                    //Log.d("lista", datos.toString());

                } else {

                }
            }

            @Override
            public void onFailure(Call<Object> callMoviles, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}