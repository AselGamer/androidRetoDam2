package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juegalmi.R;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.DetalleTransaccion;
import com.example.juegalmi.model.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class DetalleTransaccionAdaptador extends BaseAdapter {
    private Context context = null;
    private ArrayList<DetalleTransaccion> listaArticulos;
    private ArrayList<Integer> listaCantidad;
    private IControlFragmentos activity;

    public DetalleTransaccionAdaptador(Context context, ArrayList<DetalleTransaccion> listaArticulos, ArrayList<Integer> listaCantidad) {
        this.context = context;
        this.listaArticulos = listaArticulos;
        this.listaCantidad = listaCantidad;
    }

    @Override
    public int getCount() {
        return listaArticulos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaArticulos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View fila = inflater.inflate(R.layout.fila_detalle_transaccion, null);

        TextView txtArticulo = fila.findViewById(R.id.txtArticulo);
        txtArticulo.setText(listaArticulos.get(i).getIdarticulo().getNombre());

        TextView txtCantidadPrecio = fila.findViewById(R.id.txtCantidadPrecio);
        txtCantidadPrecio.setText(listaCantidad.get(i) + "x " + listaArticulos.get(i).getPrecio_total() + "€");

        TextView txtPrecioArticulo = fila.findViewById(R.id.txtPrecioArticulo);
        txtPrecioArticulo.setText(listaCantidad.get(i) * listaArticulos.get(i).getPrecio_total() + "€");

        return fila;
    }
}
