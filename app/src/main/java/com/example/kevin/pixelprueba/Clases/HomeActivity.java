package com.example.kevin.pixelprueba.Clases;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kevin.pixelprueba.Adaptadores.PagerAdapter;
import com.example.kevin.pixelprueba.Fragmentos.Login;
import com.example.kevin.pixelprueba.Fragmentos.TabProductos;
import com.example.kevin.pixelprueba.Fragmentos.TabServicios;
import com.example.kevin.pixelprueba.R;

public class HomeActivity extends AppCompatActivity implements TabProductos.OnFragmentInteractionListener, TabServicios.OnFragmentInteractionListener {

    TextView btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSalir = (TextView)findViewById(R.id.salir);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.pulsado = false;
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });



        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("PRODUCTOS"));
        tabLayout.addTab(tabLayout.newTab().setText("SERVICIOS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}
