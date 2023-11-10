package com.example.juegalmi.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdaptador extends RecyclerView.Adapter<RecyclerAdaptador.MiViewHolder>{

    private Bundle bundle = new Bundle();
    private Context context;
    private ArrayList<Imagen> listaFotos, listaFotosFiltro;

    public RecyclerAdaptador(Context context, ArrayList<Imagen> listaFotos) {
        this.context = context;
        this.listaFotos = listaFotos;
        listaFotosFiltro = new ArrayList<>();
        listaFotosFiltro.addAll(listaFotos);
    }

    @NonNull
    @Override
    public RecyclerAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagen_texto, parent, false);
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

        /*if(position == 10){
            holder.imagen.getLayoutParams().width = 100;
            holder.imagen.getLayoutParams().height = 100;
        }*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DE AQUI A DETALLE VIDEOJUEGO, CONSOLA, MOVIL...
            }
        });
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

    public void filtrar(String filtro){
        if(filtro.length() == 0){
            listaFotos.clear();
            listaFotos.addAll(listaFotosFiltro);
        }else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Imagen> collect = listaFotos.stream()
                        .filter(i -> i.getTexto1().toLowerCase().contains(filtro.toLowerCase()))
                        .collect(Collectors.toList());
                listaFotos.clear();
                listaFotos.addAll(collect);
            }else{
                listaFotos.clear();
                for(Imagen i : listaFotosFiltro){
                    if(i.getTexto1().toLowerCase().contains(filtro.toLowerCase())){
                        listaFotos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
