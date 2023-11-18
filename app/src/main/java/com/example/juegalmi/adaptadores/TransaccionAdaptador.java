package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juegalmi.Pedido;
import com.example.juegalmi.R;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class TransaccionAdaptador extends BaseAdapter {
    private Context context = null;
    private List<Transaccion> listaTransacciones;
    private IControlFragmentos activity;

    public TransaccionAdaptador(Context context, List<Transaccion> listaTransacciones) {
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
        LayoutInflater inflater = LayoutInflater.from(context);

        View fila = inflater.inflate(R.layout.fila_transaccion, null);

        TextView txtNumTransaccion = fila.findViewById(R.id.txtNumTransaccion);
        txtNumTransaccion.setText("Nº " + listaTransacciones.get(i).getIdtransaccion());
        TextView txtFechaTransaccion = fila.findViewById(R.id.txtFechaTransaccion);
        txtFechaTransaccion.setText(listaTransacciones.get(i).getFecha());
        TextView txtPrecioTransaccion = fila.findViewById(R.id.txtPrecioTransaccion);
        float precioTotal = 0;
        for(int j=0; i<listaTransacciones.get(i).getDetalles().length; j++){
            precioTotal = precioTotal + listaTransacciones.get(i).getDetalles()[j].getPrecio_total();
        }
        txtPrecioTransaccion.setText(precioTotal + "€");

        Button btnDetalle = fila.findViewById(R.id.btnDetalle);
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Pedido");
                activity.cambiarFragmento(new Pedido());
            }
        });

        return fila;
    }
}
