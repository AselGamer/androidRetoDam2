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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.juegalmi.interfaces.IControlFragmentos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosPersonales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosPersonales extends Fragment {

    private EditText edtEmail, edtNombre, edtApellido1, edtApellido2, edtTelefono, edtDireccion, edtNumDireccion, edtPiso, edtCp, edtCiudad, edtProvincia, edtPais;
    private IControlFragmentos activity;
    private Button btnFoto;
    private ImageView mPhotoImageView;
    private String mCurrentPhotoPath;
    private Uri photoUri;
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

        btnFoto = vista.findViewById(R.id.btnFoto);
        mPhotoImageView = vista.findViewById(R.id.imgUsuario);

        return vista;
    }

    //Se ejecuta al cargar el fragmento
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtEmail.setText(activity.obtenerSesion().getEmail());
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

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });

        cargarImagenes();

        /*GridView gvMiniaturas = findViewById(R.id.gvImagenes);
        FotosGridViewAdapter adapter = new FotosGridViewAdapter(this);
        gvMiniaturas.setAdapter(adapter);*/


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
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
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