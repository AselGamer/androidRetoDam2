package com.example.juegalmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.juegalmi.botonesAbajo.Galeria;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.botonesAbajo.Reparaciones;
import com.example.juegalmi.botonesAbajo.SobreNosotros;
import com.example.juegalmi.botonesAbajo.Ubicacion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);
        //Muestra el fragment inicial cuando la aplicación se inicia
        bottomNavigationView.setSelectedItemId(R.id.productos);
    }

    private boolean onItemSelectedListener(MenuItem item) {
        if(item.getItemId() == R.id.productos) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Productos())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.reparaciones) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Reparaciones())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.ubicacion) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Ubicacion())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.galeria) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Galeria())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.nosotros) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new SobreNosotros())
                    .commit();
            return true;
        }

        return false;
    }
}