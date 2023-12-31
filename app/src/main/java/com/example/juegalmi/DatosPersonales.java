package com.example.juegalmi;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.juegalmi.interfaces.IControlFragmentos;
import com.example.juegalmi.io.ApiAdaptador;
import com.example.juegalmi.model.Respuesta;
import com.example.juegalmi.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosPersonales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosPersonales extends Fragment {

    private EditText edtEmail, edtPassword, edtRepassword, edtNombre, edtApellido1, edtApellido2, edtTelefono, edtDireccion, edtNumDireccion, edtPiso, edtCp, edtCiudad, edtProvincia, edtPais;
    private IControlFragmentos activity;
    private Button btnEnviar, btnCambiarFoto, btnQuitarFoto;
    private ImageView mPhotoImageView;
    private String mCurrentPhotoPath;
    private Uri photoUri;
    private MultipartBody.Part foto;
    private List<String> imagenesList = new ArrayList<>();  //Atributo para guardar las imágenes y creamos un Getter
    private ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        doSomeOperations();
                    }
                }
            });

    public DatosPersonales() {
        // Required empty public constructor
    }

    public static DatosPersonales newInstance(String param1, String param2) {
        DatosPersonales fragment = new DatosPersonales();
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
        //iniciarSesion = (IControlFragmentos) context;
    }

    //Es el metodo para cargar el layout asociado al fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_datos_personales, container, false);

        edtEmail = vista.findViewById(R.id.edtEmail);
        edtPassword = vista.findViewById(R.id.edtPassword);
        edtRepassword = vista.findViewById(R.id.edtRepassword);
        edtNombre = vista.findViewById(R.id.edtNombre);
        edtApellido1 = vista.findViewById(R.id.edtApellido1);
        edtApellido2 = vista.findViewById(R.id.edtApellido2);
        edtTelefono = vista.findViewById(R.id.edtTelefono);
        edtDireccion = vista.findViewById(R.id.edtDireccion);
        edtNumDireccion = vista.findViewById(R.id.edtNumDireccion);
        edtPiso = vista.findViewById(R.id.edtPiso);
        edtCp = vista.findViewById(R.id.edtCp);
        edtCiudad = vista.findViewById(R.id.edtCiudad);
        edtProvincia = vista.findViewById(R.id.edtProvincia);
        edtPais = vista.findViewById(R.id.edtPais);

        btnEnviar = vista.findViewById(R.id.btnEnviar);

        btnCambiarFoto = vista.findViewById(R.id.btnCambiarFoto);
        btnQuitarFoto = vista.findViewById(R.id.btnQuitarFoto);
        mPhotoImageView = vista.findViewById(R.id.imgUsuario);

        return vista;
    }

    //Se ejecuta al cargar el fragmento
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtEmail.setText(activity.obtenerSesion().getEmail());
        edtPassword.setText(activity.obtenerSesion().getPassword());
        edtNombre.setText(activity.obtenerSesion().getNombre());
        edtApellido1.setText(activity.obtenerSesion().getApellido1());
        edtApellido2.setText(activity.obtenerSesion().getApellido2());
        edtTelefono.setText(activity.obtenerSesion().getTelefono());
        edtDireccion.setText(activity.obtenerSesion().getCalle());
        edtNumDireccion.setText(activity.obtenerSesion().getNumPortal());
        edtPiso.setText(activity.obtenerSesion().getPiso());
        edtCp.setText(activity.obtenerSesion().getCodigoPostal());
        edtCiudad.setText(activity.obtenerSesion().getCiudad());
        edtProvincia.setText(activity.obtenerSesion().getProvincia());
        edtPais.setText(activity.obtenerSesion().getPais());
        Glide
                .with(view.getContext())
                .load("https://retoasel.duckdns.org/userimages/"+ activity.obtenerSesion().getFoto())
                .centerCrop()
                .into(mPhotoImageView);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario(edtNombre.getText().toString(), edtApellido1.getText().toString(), edtApellido2.getText().toString(), edtEmail.getText().toString(),
                        edtPassword.getText().toString(), edtTelefono.getText().toString(), edtDireccion.getText().toString(), edtNumDireccion.getText().toString(),
                        edtPiso.getText().toString(), edtCp.getText().toString(), edtCiudad.getText().toString(), edtProvincia.getText().toString(), edtPais.getText().toString(), "");



                if (edtPassword.getText().toString().equals(edtRepassword.getText().toString()) && edtPassword.getText().toString().length() > 0)
                {
                    Call<Respuesta> call = ApiAdaptador.getApiService().actualizarUsuario("Bearer " + activity.obtenerToken(), usuario);
                    call.enqueue(new Callback<Respuesta>() {
                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getContext(), response.body().getData(), Toast.LENGTH_SHORT).show();
                                if (foto != null)
                                {
                                    Call<JsonObject> callFoto = ApiAdaptador.getApiService().ponerFoto("Bearer " + activity.obtenerToken(), foto);
                                    callFoto.enqueue(new Callback<JsonObject>() {
                                        @Override
                                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                            try {
                                                if (response.isSuccessful())
                                                {
                                                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                                                    usuario.setFoto(jsonObject.get("nombre").toString());
                                                    activity.cambiarSesion(usuario);
                                                }

                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                            //usuario.setFoto(activity.obtenerSesion().getFoto());
                                        }

                                        @Override
                                        public void onFailure(Call<JsonObject> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    activity.cambiarSesion(usuario);
                                }


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
                } else {
                    Toast.makeText(getContext(), "Debes rellenar las dos contraseñas para editar tus datos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });

        cargarImagenes();

        btnQuitarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<JsonObject> callQuitar = ApiAdaptador.getApiService().quitarFoto("Bearer " + activity.obtenerToken());
                callQuitar.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful())
                        {
                            Usuario usuario = activity.obtenerSesion();
                            usuario.setFoto("");
                            activity.cambiarSesion(usuario);
                            foto = null;
                            Glide
                                    .with(view.getContext())
                                    .clear(mPhotoImageView);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });


    }

    /////////////////////PARA LA CAMARA/////////////////////////

    private void abrirCamara()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Mirar si se carga correctamente en controlador de la camara
        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) == null)
        {
            //Creamos la variable foto
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Si la foto se ha creado le damos valores: fecha, titulo, descripcion...
            if(photoFile != null)
            {
                ContentValues values = new ContentValues();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String date = dateFormat.format(new Date());
                values.put(MediaStore.Images.Media.TITLE, "Picture" + date + ".jpg");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                photoUri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                someActivityResultLauncher.launch(takePictureIntent);
            } else
            {
                Toast.makeText(getActivity(), "No se puede abrir la camara", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Funcion para crear una imagen temporal: crea el archivo temporal en el que se guardará el resultado del activity de la cámara
    private File createImageFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //Viene del onActivityResult() que esta al principio
    protected void doSomeOperations()
    {
        Bitmap bitmap = null;
        try {
            Glide
                    .with(getContext())
                    .load(photoUri)
                    .into(mPhotoImageView);
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri); //de la ruta temporal que hemos creado en photoURI
            mPhotoImageView.setImageBitmap(bitmap); //el bitmap cargado se muestra en un IMAGEVIEW
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Guardamos la imagen en una ruta
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String ruta = guardarFoto(byteArray);

        //Creamos un toast para ver donde se ha guardado la foto
        Toast.makeText(getActivity().getApplicationContext(), "Foto cargada en " + ruta, Toast.LENGTH_SHORT).show();
    }

    //Se crea una carpeta para almacenar las imagenes
    private String guardarFoto(byte[] data)
    {
        String filename = "";
        //Se crea la carpeta
        File nuevaCarpeta = new File(getActivity().getFilesDir(), "Galeria");
        if(!nuevaCarpeta.exists() && !nuevaCarpeta.mkdirs())
        {
            Toast.makeText(getActivity().getApplicationContext(), "No se pudo crear la carpeta", Toast.LENGTH_SHORT).show();
        } else
        {
            //Se crea el archivo
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = dateFormat.format(new Date());
            String photoFile = "Picture_" + date + ".jpg";
            filename = nuevaCarpeta.getPath() + File.separator + photoFile;
            File pictureFile = new File(filename);
            RequestBody requestFile = RequestBody.create(pictureFile, MediaType.parse("multipart/form-data"));
            this.foto = MultipartBody.Part.createFormData("foto", pictureFile.getName(), requestFile);

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                Toast.makeText(getActivity().getApplicationContext(), "Imagen guardada", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        cargarImagenes();
        /*GridView gvMiniaturas = findViewById(R.id.gvImagenes);
        FotosGridViewAdapter adapter = new FotosGridViewAdapter(this);
        gvMiniaturas.setAdapter(adapter);*/
        return filename;
    }

    //Funcion para cargar las imagenes
    private void cargarImagenes()
    {
        imagenesList.clear();
        File nuevaCarpeta = new File(getActivity().getFilesDir(), "Galeria");
        if(!nuevaCarpeta.exists())
        {
            Toast.makeText(getActivity().getApplicationContext(), "No se pudo cargar la galeria", Toast.LENGTH_SHORT).show();
        } else
        {
            File[] fotos = nuevaCarpeta.listFiles();
            for(int i = 0; i < fotos.length; i++)
            {
                File foto = fotos[i];
                imagenesList.add(foto.getAbsolutePath());
            }
        }
    }

    //El Getter
    public List<String> getImagenesList() {
        return imagenesList;
    }
}