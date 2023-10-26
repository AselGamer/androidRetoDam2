package com.example.juegalmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.juegalmi.adaptadores.PagerAdaptador;
import com.example.juegalmi.botonesAbajo.Galeria;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.botonesAbajo.Reparaciones;
import com.example.juegalmi.botonesAbajo.SobreNosotros;
import com.example.juegalmi.botonesAbajo.Ubicacion;
import com.example.juegalmi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //replaceFragment(new Productos());

        /*bottomNavigationView = findViewById(R.id.bottomNavigationView);



        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.productos){
                replaceFragment(new Productos());
            }else if(item.getItemId() == R.id.reparaciones){
                replaceFragment(new Reparaciones());
            }else if(item.getItemId() == R.id.ubicacion){
                replaceFragment(new Ubicacion());
            }else if(item.getItemId() == R.id.galeria){
                replaceFragment(new Galeria());
            }else if(item.getItemId() == R.id.nosotros){
                replaceFragment(new SobreNosotros());
            }

            return true;
        });*/

        //setupToolbar();
    }

    /*private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }*/

    /*private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }*/
}