package com.example.juegalmi.adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.juegalmi.tabsArriba.Consolas;
import com.example.juegalmi.tabsArriba.Moviles;
import com.example.juegalmi.tabsArriba.Todo;
import com.example.juegalmi.tabsArriba.Videojuegos;

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
            return new Todo();
        }else if(position == 1){
            return new Videojuegos();
        }else if(position == 2){
            return new Consolas();
        }else if(position == 3){
            return new Moviles();
        }

        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
