package com.example.juegalmi.botonesAbajo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.juegalmi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubicacion extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    //private EditText txtLatitud, txtLongitud;
    private GoogleMap mMap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_ubicacion, container, false);

        /*txtLatitud = vista.findViewById(R.id.txtLatitud);
        txtLongitud = vista.findViewById(R.id.txtLongitud);*/

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        return vista;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        /*txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);*/
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        /*txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);*/
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        //Agregamos un marcador
        LatLng juegAlmi = new LatLng(43.27172023125758,-2.9487642645835876);
        mMap.addMarker(new MarkerOptions().position(juegAlmi).title("JuegAlmi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(juegAlmi));
    }
}