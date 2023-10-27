package com.example.juegalmi.botonesAbajo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juegalmi.R;
import com.example.juegalmi.adaptadores.PagerAdaptador;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Productos extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabTodo, tabVideojuegos, tabConsolas, tabMoviles;
    PagerAdaptador pagerAdaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_productos, container, false);

        //Aprovechamos para recoger(instanciar) los botones y demas
        tabLayout = vista.findViewById(R.id.tabLayout);
        viewPager = vista.findViewById(R.id.pager);
        tabTodo = vista.findViewById(R.id.tabTodo);
        tabVideojuegos = vista.findViewById(R.id.tabVideojuegos);
        tabConsolas = vista.findViewById(R.id.tabConsolas);
        tabMoviles = vista.findViewById(R.id.tabMoviles);

        pagerAdaptador = new PagerAdaptador(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdaptador);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    pagerAdaptador.notifyDataSetChanged();
                }
                if(tab.getPosition() == 1){
                    pagerAdaptador.notifyDataSetChanged();
                }
                if(tab.getPosition() == 2){
                    pagerAdaptador.notifyDataSetChanged();
                }
                if(tab.getPosition() == 3){
                    pagerAdaptador.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        /*TRANSITION
        TransitionInflater transitionInflater=TransitionInflater.from(requireContext());
        setEnterTransition(transitionInflater.inflateTransition(R.transition.slide));
         */

        return vista;
    }
}