package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegalmi.R;
import com.example.juegalmi.model.Articulo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TipoAdaptador extends BaseAdapter {
    private Context context = null;
    private LinkedHashMap<String, List<Articulo>> tipoArticulos;
    private String[] tipos;

    public TipoAdaptador(Context context, HashMap<String, List<Articulo>> tipoArticulos) {
        this.context = context;
        this.tipoArticulos = new LinkedHashMap<String, List<Articulo>>(tipoArticulos);
        this.tipos = tipoArticulos.keySet().toArray(new String[tipoArticulos.size()]);
    }

    @Override
    public int getCount() {
        return tipoArticulos.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoArticulos.get(tipos[i]);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View fila = inflater.inflate(R.layout.tipo_articulo_list, null);

        TextView textoNombre = fila.findViewById(R.id.txtSubtitulo);
        textoNombre.setText(tipos[i]);

        ArrayList<Articulo> ar = new ArrayList<Articulo>(this.tipoArticulos.get(tipos[i]));

        RecyclerAdaptador recyclerAdaptador = new RecyclerAdaptador(fila.getContext(), ar, false);  //añadir tipos[i] para saber que fragmento hay que cambiar

        RecyclerView recyclerView = fila.findViewById(R.id.recycler);

        recyclerView.setAdapter(recyclerAdaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(fila.getContext(), LinearLayoutManager.HORIZONTAL, false));

        return fila;
    }
}
