package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.juegalmi.R;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.DetalleTransaccion;
import com.example.juegalmi.model.Reparacion;
import com.example.juegalmi.model.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class DetalleTransaccionAdaptador extends BaseAdapter {
    private Context context = null;
    private ArrayList<DetalleTransaccion> listaArticulos;
    private ArrayList<Integer> listaCantidad;
    private Transaccion transaccion;
    private Reparacion reparacion;
    private IControlFragmentos activity;
    private View barra;

    public DetalleTransaccionAdaptador(Context context, ArrayList<DetalleTransaccion> listaArticulos, ArrayList<Integer> listaCantidad) {
        this.context = context;
        this.listaArticulos = listaArticulos;
        this.listaCantidad = listaCantidad;
    }

    public DetalleTransaccionAdaptador(Context context, Transaccion transaccion) {
        this.context = context;
        this.transaccion = transaccion;
    }

    public DetalleTransaccionAdaptador(Context context, Reparacion reparacion) {
        this.context = context;
        this.reparacion = reparacion;
    }

    @Override
    public int getCount() {
        if(transaccion == null && reparacion == null){
            return listaArticulos.size();
        }else{
            return 1;
        }
    }

    @Override
    public Object getItem(int i) {
        if(reparacion == null){
            if(transaccion == null){
                return listaArticulos.get(i);
            }else{
                return transaccion;
            }
        }else{
            return reparacion;
        }
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
        TextView txtCantidadPrecio = fila.findViewById(R.id.txtCantidadPrecio);
        TextView txtPrecioArticulo = fila.findViewById(R.id.txtPrecioArticulo);
        TextView txtFechaInicio = fila.findViewById(R.id.txtFechaInicio);
        TextView txtFechaFin = fila.findViewById(R.id.txtFechaFin);
        View barra = fila.findViewById(R.id.barra);
        LinearLayout.LayoutParams paramsCompra = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams paramsAlquiler = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if(reparacion == null){
            if(transaccion == null){    //Compra
                paramsAlquiler.height = 0;
                paramsAlquiler.width = 0;
                paramsCompra.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                paramsCompra.width = LinearLayout.LayoutParams.WRAP_CONTENT;

                txtCantidadPrecio.setVisibility(View.VISIBLE);
                txtCantidadPrecio.setLayoutParams(paramsCompra);
                txtFechaInicio.setVisibility(View.INVISIBLE);
                txtFechaInicio.setLayoutParams(paramsAlquiler);
                txtFechaFin.setVisibility(View.INVISIBLE);
                txtFechaFin.setLayoutParams(paramsAlquiler);

                txtArticulo.setText(listaArticulos.get(i).getIdarticulo().getNombre());
                txtCantidadPrecio.setText(listaCantidad.get(i) + "x " + listaArticulos.get(i).getPrecio_total() + "€");
                txtPrecioArticulo.setText(listaCantidad.get(i) * listaArticulos.get(i).getPrecio_total() + "€");
            }else{  //Alquiler
                paramsCompra.height = 0;
                paramsCompra.width = 0;
                paramsAlquiler.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                paramsAlquiler.width = LinearLayout.LayoutParams.WRAP_CONTENT;

                txtCantidadPrecio.setVisibility(View.INVISIBLE);
                txtCantidadPrecio.setLayoutParams(paramsCompra);
                txtFechaInicio.setVisibility(View.VISIBLE);
                txtFechaInicio.setLayoutParams(paramsAlquiler);
                txtFechaFin.setVisibility(View.VISIBLE);
                txtFechaFin.setLayoutParams(paramsAlquiler);

                txtArticulo.setText(transaccion.getDetalles()[0].getIdarticulo().getNombre());
                txtFechaInicio.setText("Fecha Inicio: " + transaccion.getFecha_inicio());
                txtFechaFin.setText("Fecha Fin: " + transaccion.getFecha_fin());
                txtPrecioArticulo.setText(transaccion.getPrecio() + "€");
            }
        }else{  //Reparacion
            txtArticulo.setText("Problema: " + reparacion.getProblema());
            txtCantidadPrecio.setText("Estado: " + reparacion.getIdestadoreparacion().getNombre());
            txtFechaInicio.setText("Fecha: " + reparacion.getFechaInicio());
            if(reparacion.getComentarioReparacion() == null){
                txtFechaFin.setText("Comentario: ");
            }else{
                txtFechaFin.setText("Comentario: " + reparacion.getComentarioReparacion());
            }
            if(reparacion.getFechaFin() != null){
                txtPrecioArticulo.setText(reparacion.getPrecio() + "€");
            }else{
                txtPrecioArticulo.setText("");
                barra.setVisibility(View.INVISIBLE);
            }
        }

        return fila;
    }
}
