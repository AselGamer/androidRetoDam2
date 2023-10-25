package com.example.juegalmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.juegalmi.adaptadores.PagerAdaptador;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tabInterior, tabExterior;
    PagerAdaptador pagerAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        //Aprovechamos para recoger(instanciar) los botones y demas
        tabLayout = findViewById(R.id.tabLayoutAbajo);
        viewPager = findViewById(R.id.pager);
        tabInterior = findViewById(R.id.tabInterior);
        tabExterior = findViewById(R.id.tabExterior);

        pagerAdaptador = new PagerAdaptador(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdaptador);*/
    }
}