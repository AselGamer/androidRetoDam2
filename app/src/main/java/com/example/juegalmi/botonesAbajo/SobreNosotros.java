package com.example.juegalmi.botonesAbajo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.juegalmi.R;

public class SobreNosotros extends Fragment {

    private ImageView imgInstagram;
    private ImageView imgTwitter;
    private ImageView imgTikTok;
    private String urlInstagram;
    private String urlTwitter;
    private String urlTikTok;

    public SobreNosotros() {

    }

    //Se ejecuta cuando se crea el fragmento, no necesariamente si se muestra
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    //Es el metodo para cargar el layout asociado al fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_sobre_nosotros, container, false);
        imgInstagram = vista.findViewById(R.id.imgInstagram);
        imgTwitter = vista.findViewById(R.id.imgTwitter);
        imgTikTok = vista.findViewById(R.id.imgTikTok);

        urlInstagram = "https://www.instagram.com/";
        urlTwitter = "https://twitter.com/";
        urlTikTok = "https://www.tiktok.com/es/";

        return vista;
    }

    //Se ejecuta al cargar el fragmento
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(urlInstagram);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(urlTwitter);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgTikTok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(urlTikTok);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

}