package com.example.juegalmi.adaptadores;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.juegalmi.Cesta;
import com.example.juegalmi.R;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Producto;

import java.util.ArrayList;

public class CestaAdaptador extends RecyclerView.Adapter<CestaAdaptador.MiViewHolder> {
    private Context context = null;
    private IControlFragmentos activity;
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<Integer> listaCantidad;
    private Bundle bundle = new Bundle();

    public CestaAdaptador(Context context, ArrayList<Articulo> listaArticulos, ArrayList<Integer> listaCantidad) {
        this.context = context;
        this.listaArticulos = listaArticulos;
        this.listaCantidad = listaCantidad;
    }

    @NonNull
    @Override
    public CestaAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_cesta, parent, false);

        return new MiViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CestaAdaptador.MiViewHolder holder, int position) {
        Glide
                .with(context)
                .load("https://retoasel.duckdns.org/images/" + listaArticulos.get(position).getFoto())
                .into(holder.imgFoto);

        holder.txtPrecioTotal.setText(listaCantidad.get(position) * listaArticulos.get(position).getPrecio() + "€");
        if(listaArticulos.get(position).getArticulonombre() == null){
            holder.txtNombre.setText(listaArticulos.get(position).getNombre());
        }else{
            holder.txtNombre.setText(listaArticulos.get(position).getArticulonombre());
        }
        holder.txtCantidadPrecio.setText(listaCantidad.get(position) + "x " + String.valueOf(listaArticulos.get(position).getPrecio()) + "€");

        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.quitarListaArticulosCesta(position);
                activity.quitarListaCantidad(position);
                activity.cambiarTitulo("Cesta (" + activity.obtenerListaArticulos().size() + ")");
                bundle.putSerializable("listaArticulos", listaArticulos);
                activity.cambiarFragmento(Cesta.newInstance(bundle));
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        activity = (IControlFragmentos) context;
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    class MiViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFoto;
        TextView txtPrecioTotal, txtNombre, txtCantidadPrecio;
        ImageButton btnBorrar;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            txtPrecioTotal = itemView.findViewById(R.id.txtPrecioTotal);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtCantidadPrecio = itemView.findViewById(R.id.txtCantidadPrecio);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
        }
    }

    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(ArrayList<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }
}

