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
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Respuesta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

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
    private TextView txtPrueba;
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

        txtPrueba = vista.findViewById(R.id.txtPrueba);
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
                /*if(edtEmail.getText().toString().equals("Almi") && edtContrasenya.getText().toString().equals("Almi123")){
                    activity.cambiarTitulo("Productos");
                    activity.cambiarSesion("Almi");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedor, new Productos())
                            .commit();
                }else{

                }*/

                /*Gson gson = new GsonBuilder()
                        .setLenient()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .create();*/
                btnEnviar.startAnimation(animation);

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
                });
            }
        });

        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = ApiAdaptador.getApiService().getAutorizacion("Bearer " + token);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            try {
                                Toast.makeText(getContext(), response.body().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            Log.d("Dam2", response.message());
                            Toast.makeText(getContext(), "El Token no es correcto", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        txtPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPrueba.startAnimation(animation);
            }
        });
    }
}