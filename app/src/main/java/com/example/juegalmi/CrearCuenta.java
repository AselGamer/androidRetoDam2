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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Login;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrearCuenta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearCuenta extends Fragment {
    private EditText edtEmail, edtNombre, edtApellido1, edtApellido2, edtPassword, edtTelefono, edtDireccion, edtNumDireccion, edtPiso, edtCp, edtCiudad, edtProvincia, edtPais;
    private Button btnEnviar;
    private IControlFragmentos activity;
    private int ver = 0;

    public CrearCuenta() {
        // Required empty public constructor
    }

    public static CrearCuenta newInstance(String param1, String param2) {
        CrearCuenta fragment = new CrearCuenta();
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (IControlFragmentos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_crear_cuenta, container, false);

        edtEmail = vista.findViewById(R.id.edtEmail);
        edtNombre = vista.findViewById(R.id.edtNombre);
        edtApellido1 = vista.findViewById(R.id.edtApellido1);
        edtApellido2 = vista.findViewById(R.id.edtApellido2);
        edtPassword = vista.findViewById(R.id.edtPassword);
        edtTelefono = vista.findViewById(R.id.edtTelefono);
        edtDireccion = vista.findViewById(R.id.edtDireccion);
        edtNumDireccion = vista.findViewById(R.id.edtNumDireccion);
        edtPiso = vista.findViewById(R.id.edtPiso);
        edtCp = vista.findViewById(R.id.edtCp);
        edtCiudad = vista.findViewById(R.id.edtCiudad);
        edtProvincia = vista.findViewById(R.id.edtProvincia);
        edtPais = vista.findViewById(R.id.edtPais);
        btnEnviar = vista.findViewById(R.id.btnEnviar);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario(edtNombre.getText().toString(), edtApellido1.getText().toString(), edtApellido2.getText().toString(), edtEmail.getText().toString(),
                        edtPassword.getText().toString(), edtTelefono.getText().toString(), edtDireccion.getText().toString(), edtNumDireccion.getText().toString(),
                        edtPiso.getText().toString(), edtCp.getText().toString(), edtCiudad.getText().toString(), edtProvincia.getText().toString(), edtPais.getText().toString());

                Call<Respuesta> call = ApiAdaptador.getApiService().crearUsuario(usuario);
                call.enqueue(new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(), response.body().getData(), Toast.LENGTH_SHORT).show();
                            //data = response.body().getData();
                        }else{
                            Log.d("Dam2", response.message());
                            Toast.makeText(getContext(), "Los campos no son correctos", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Respuesta> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}