package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.os.Bundle;
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
import com.example.juegalmi.model.Reparacion;
import com.example.juegalmi.model.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class TransaccionAdaptador extends BaseAdapter {
    private Bundle bundle = new Bundle();
    private Context context = null;
    private List<Transaccion> listaTransacciones;
    private List<Reparacion> listaReparaciones;
    private IControlFragmentos activity;
    private boolean reparacion = false;

    public TransaccionAdaptador(Context context, List<Transaccion> listaTransacciones) {
        this.context = context;
        this.listaTransacciones = listaTransacciones;
        activity = (IControlFragmentos) context;
    }

    public TransaccionAdaptador(Context context, List<Reparacion> listaReparaciones, boolean reparacion) {
        this.context = context;
        this.listaReparaciones = listaReparaciones;
        this.reparacion = reparacion;
        activity = (IControlFragmentos) context;
    }

    @Override
    public int getCount() {
        if(reparacion == false){
            return listaTransacciones.size();
        }else{
            return listaReparaciones.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if(reparacion == false){
            return listaTransacciones.get(i);
        }else{
            return listaReparaciones.get(i);
        }
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
        TextView txtFechaTransaccion = fila.findViewById(R.id.txtFechaTransaccion);
        TextView txtPrecioTransaccion = fila.findViewById(R.id.txtPrecioTransaccion);
        if(reparacion == false){
            txtNumTransaccion.setText("Nº " + listaTransacciones.get(i).getIdtransaccion());
            if(listaTransacciones.get(i).getFecha() == null){   //ya que Alquiler no tiene el campo fecha
                txtFechaTransaccion.setText(listaTransacciones.get(i).getFecha_inicio());

                txtPrecioTransaccion.setText(listaTransacciones.get(i).getPrecio() + "€");
            }else{
                txtFechaTransaccion.setText(listaTransacciones.get(i).getFecha());

                float precioTotal = 0;
                for(int j=0; j<listaTransacciones.get(i).getDetalles().length; j++){
                    precioTotal = precioTotal + listaTransacciones.get(i).getDetalles()[j].getPrecio_total();
                }
                txtPrecioTransaccion.setText(precioTotal + "€");
            }
        }else{
            txtNumTransaccion.setText("Nº " + listaReparaciones.get(i).getIdreparacion());
            txtFechaTransaccion.setText(listaReparaciones.get(i).getFechaInicio());
            txtPrecioTransaccion.setText("Estado: " + listaReparaciones.get(i).getIdestadoreparacion().getNombre());
        }

        Button btnDetalle = fila.findViewById(R.id.btnDetalle);
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reparacion == false){
                    Transaccion transaccion = listaTransacciones.get(i);
                    bundle.putSerializable("transaccion", transaccion);
                }else{
                    Reparacion reparacion = listaReparaciones.get(i);
                    bundle.putSerializable("reparacion", reparacion);
                }
                activity.cambiarTitulo("Pedido");
                activity.cambiarFragmento(Pedido.newInstance(bundle));
            }
        });

        return fila;
    }
}
