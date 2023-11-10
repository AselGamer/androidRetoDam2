package com.example.juegalmi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.io.ApiServicio;
import com.example.juegalmi.model.Articulo;
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Marca;
import com.example.juegalmi.model.Respuesta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IniciarSesion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IniciarSesion extends Fragment {

    private TextView txtRegistrate;
    private Button btnEnviar, btnToken;
    private EditText edtEmail;
    private EditText edtContrasenya;
    private IControlFragmentos activity;
    private Call<ArrayList<Login>> call;
    private String token = "";
    private Animation animation;

    public IniciarSesion() {

    }

    public static IniciarSesion newInstance(String param1, String param2) {
        IniciarSesion fragment = new IniciarSesion();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    //Metodo para interactuar con la actividad principal
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    //Es el metodo para cargar el layout asociado al fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);

        txtRegistrate = vista.findViewById(R.id.txtRegistrate);
        btnEnviar = vista.findViewById(R.id.btnEnviar);
        edtEmail = vista.findViewById(R.id.edtEmail);
        edtContrasenya = vista.findViewById(R.id.edtContrasenya);

        btnToken = vista.findViewById(R.id.btnToken);

        animation = AnimationUtils.loadAnimation(vista.getContext(), R.anim.mover);

        return vista;
    }

    //Se ejecuta al cargar el fragmento
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Retrofit
        /*Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.0.129:8000/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ApiServicio apiServicio = retrofit.create(ApiServicio.class);*/

        txtRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.cambiarTitulo("Crear Cuenta");

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contenedor, new CrearCuenta())
                        .commit();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //INICIAR SESION: Contraseña inventada
                if(edtEmail.getText().toString().equals("Almi") && edtContrasenya.getText().toString().equals("Almi123")){
                    activity.cambiarTitulo("");
                    activity.cambiarSesion("Almi");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new Productos())
                            .commit();
                }else{

                }

                /*Gson gson = new GsonBuilder()
                        .setLenient()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();*/

                //INICIAR SESION: Retrofit
                /*btnEnviar.startAnimation(animation);

                Login login = new Login("example@email.com", "Almi123");
                Call<Respuesta> call = ApiAdaptador.getApiService().login(login);

                call.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(), response.body().getToken(), Toast.LENGTH_SHORT).show();
                            token = response.body().getToken();
                        }else{
                            Log.d("Dam2", response.message());
                            Toast.makeText(getContext(), "El Login no es correcto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });

        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Articulo>> call = ApiAdaptador.getApiService().getAutorizacion("Bearer " + token);
                call.enqueue(new Callback<List<Articulo>>() {
                    @Override
                    public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                        if(response.isSuccessful()){
                            List<Articulo> lr = response.body();
                            Log.d("token", lr.get(0).getArticulonombre());
                            //Log.d("token", response);
                        }else{
                            Log.d("Dam2", response.message());
                            Toast.makeText(getContext(), "El Token no es correcto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Articulo>> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //parseJSON
    private List<Articulo> parseJson(JSONArray jsonArray)
    {
        List<Articulo> listArticulos = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++)
        {
            try {
                JSONObject objetoArticulo = jsonArray.getJSONObject(i);
                Articulo articulo = new Articulo(objetoArticulo.getString("idarticulo"),
                        objetoArticulo.getString("articulonombre"),
                        objetoArticulo.getString("tipoarticulo"),
                        (float) objetoArticulo.getDouble("precio"),
                        objetoArticulo.getInt("stock"),
                        (Marca) objetoArticulo.get("idmarca"),
                        objetoArticulo.getInt("idtipoClase"));
                listArticulos.add(articulo);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return listArticulos;
    }
}