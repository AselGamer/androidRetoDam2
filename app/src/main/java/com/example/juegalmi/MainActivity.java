package com.example.juegalmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.juegalmi.botonesAbajo.Galeria;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.botonesAbajo.Reparaciones;
import com.example.juegalmi.botonesAbajo.SobreNosotros;
import com.example.juegalmi.botonesAbajo.Ubicacion;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.io.ApiServicio;
import com.example.juegalmi.model.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IControlFragmentos {

    private BottomNavigationView bottomNavigationView;
    private TextView txtTitulo;
    private ImageButton imgUsuario, imgCesta;
    private String txtSesion = "";
    private Call<ArrayList<Login>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Callback
        /*call = ApiAdaptador.getApiService().getUsuario();
        call.enqueue(this); //lo pone en cola*/

        /*Call<Usuario> call = ApiAdaptador.getApiService().getLogin("example@email.com", "Almi123");
        call.enqueue(this);*/




        txtTitulo = findViewById(R.id.txtTitulo);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);
        //Muestra el fragment inicial cuando la aplicación se inicia
        bottomNavigationView.setSelectedItemId(R.id.productos);

        imgUsuario = findViewById(R.id.imgUsuario);
        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtSesion.equals("")){
                    txtTitulo.setText("Iniciar Sesión");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new IniciarSesion())
                            .commit();
                }else{
                    txtTitulo.setText("Hola " + txtSesion);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new MiEspacio())
                            .commit();
                }
            }
        });

        imgCesta = findViewById(R.id.imgCesta);
        imgCesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtSesion.equals("")){
                    txtTitulo.setText("Iniciar Sesión");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new IniciarSesion())
                            .commit();
                }else{
                    txtTitulo.setText("Cesta");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new Cesta())
                            .commit();
                }
            }
        });
    }

    private boolean onItemSelectedListener(MenuItem item) {
        if(item.getItemId() == R.id.productos) {
            txtTitulo.setText("Productos");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Productos())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.reparaciones) {
            txtTitulo.setText("Reparaciones");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Reparaciones())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.ubicacion) {
            txtTitulo.setText("Ubicación");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Ubicacion())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.galeria) {
            txtTitulo.setText("Galería");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new Galeria())
                    .commit();
            return true;
        }else if(item.getItemId() == R.id.nosotros) {
            txtTitulo.setText("Sobre Nosotros");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new SobreNosotros())
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public void cambiarTitulo(String titulo) {
        txtTitulo.setText(titulo);
    }

    @Override
    public void cambiarSesion(String sesion) {
        txtSesion = sesion;
    }

    @Override
    public String obtenerSesion() {
        return txtSesion;
    }
/*
    @Override
    public void onResponse(Call<ArrayList<Login>> call, Response<ArrayList<Login>> response) {
        if(response.isSuccessful()){
            ArrayList<Login> logins = response.body();
            Log.d("Dam2", "Tamaño de usuarios: " + logins.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Login>> call, Throwable t) {

    }*/
}