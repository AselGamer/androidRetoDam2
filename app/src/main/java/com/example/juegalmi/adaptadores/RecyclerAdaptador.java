package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.juegalmi.R;
import com.example.juegalmi.model.Imagen;

import java.util.ArrayList;

public class RecyclerAdaptador extends RecyclerView.Adapter<RecyclerAdaptador.MiViewHolder>{

    private Bundle bundle = new Bundle();
    private Context context;
    private ArrayList<Imagen> listaFotos;

    public RecyclerAdaptador(Context context, ArrayList<Imagen> listaFotos) {
        this.context = context;
        this.listaFotos = listaFotos;
    }

    @NonNull
    @Override
    public RecyclerAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.foto_texto, parent, false);
        return new MiViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdaptador.MiViewHolder holder, int position) {
        Glide
                .with(context)
                .load(listaFotos.get(position).getUrl())
                .into(holder.imagen);

        holder.txtJuego.setText(listaFotos.get(position).getTexto1());
        holder.txtPrecio.setText(listaFotos.get(position).getTexto2());
        holder.txtDesarrollador.setText(listaFotos.get(position).getTexto3());
    }

    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

    class MiViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView txtJuego, txtPrecio, txtDesarrollador;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imgImagen);
            txtJuego = itemView.findViewById(R.id.txtJuego);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtDesarrollador = itemView.findViewById(R.id.txtDesarrollador);
        }
    }
}
