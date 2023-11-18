package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.juegalmi.R;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.DetalleTransaccion;
import com.example.juegalmi.model.Transaccion;

import java.util.List;

public class DetalleTransaccionAdaptador extends BaseAdapter {
    private Context context = null;
    private List<DetalleTransaccion> listaDetalleTransacciones;
    private IControlFragmentos activity;

    public DetalleTransaccionAdaptador(Context context, List<DetalleTransaccion> listaDetalleTransacciones) {
        this.context = context;
        this.listaDetalleTransacciones = listaDetalleTransacciones;
    }

    @Override
    public int getCount() {
        return listaDetalleTransacciones.size();
    }

    @Override
    public Object getItem(int i) {
        return listaDetalleTransacciones.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View fila = inflater.inflate(R.layout.fila_detalle_transaccion, null);

        /////////////////////////////////
        /////////////////////////////////

        return fila;
    }
}
