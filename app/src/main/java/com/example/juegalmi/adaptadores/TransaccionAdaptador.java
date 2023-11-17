package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juegalmi.R;
import com.example.juegalmi.model.Articulo;

import java.util.ArrayList;

public class TransaccionAdaptador extends BaseAdapter {
    private Context context = null;
    private ArrayList<Articulo> listaTransacciones;

    public TransaccionAdaptador(Context context, ArrayList<Articulo> listaTransacciones) {
        this.context = context;
        this.listaTransacciones = listaTransacciones;
    }

    @Override
    public int getCount() {
        return listaTransacciones.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTransacciones.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Se instancia el inflador
        LayoutInflater inflater = LayoutInflater.from(context);

        //Cargamos la vista personalizada: inflater.inflate(la vista a cargar, grupo de vistas del spinner_personalizado, false para que no de errores)
        View fila = inflater.inflate(R.layout.fila_transaccion, null); //...parent, false);

        TextView txtNumTransaccion = fila.findViewById(R.id.txtNumTransaccion);
        txtNumTransaccion.setText(listaTransacciones.get(i).getArticulonombre());//////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        return fila;
    }
}
