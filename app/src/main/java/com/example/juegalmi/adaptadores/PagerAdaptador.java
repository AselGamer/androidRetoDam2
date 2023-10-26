package com.example.juegalmi.adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.juegalmi.botonesAbajo.Galeria;
import com.example.juegalmi.botonesAbajo.Productos;
import com.example.juegalmi.botonesAbajo.Reparaciones;
import com.example.juegalmi.botonesAbajo.SobreNosotros;
import com.example.juegalmi.botonesAbajo.Ubicacion;

public class PagerAdaptador extends FragmentPagerAdapter {
    int numTabs;

    public PagerAdaptador(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new Productos();
        }else if(position == 1){
            return new Reparaciones();
        }else if(position == 2){
            return new Ubicacion();
        }else if(position == 3){
            return new Galeria();
        }else if(position == 4){
            return new SobreNosotros();
        }

        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
