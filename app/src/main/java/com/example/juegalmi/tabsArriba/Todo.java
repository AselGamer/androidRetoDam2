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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.RecyclerAdaptador;
import com.example.juegalmi.adaptadores.TipoAdaptador;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Etiqueta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Todo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Todo extends Fragment {
    private ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
    private IControlFragmentos activity;
    private ListView listTipos = null;

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

        Call<Map<String, List<Articulo>>> call = ApiAdaptador.getApiService().getAllByType();
        call.enqueue(new Callback<Map<String, List<Articulo>>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Map<String, List<Articulo>>> call, Response<Map<String, List<Articulo>>> response) {
                if(response.isSuccessful()){
                    HashMap<String, List<Articulo>> lr = new HashMap<>(response.body());

                    TipoAdaptador tipoAdaptador = new TipoAdaptador(vista.getContext(), lr);

                    listTipos = vista.findViewById(R.id.listTipos);
                    listTipos.setAdapter(tipoAdaptador);
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }





}