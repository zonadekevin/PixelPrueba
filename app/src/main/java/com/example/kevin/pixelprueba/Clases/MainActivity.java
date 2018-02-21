package com.example.kevin.pixelprueba.Clases;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kevin.pixelprueba.Fragmentos.Inicio;
import com.example.kevin.pixelprueba.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new Inicio()).commit();



        //getSupportFragmentManager().beginTransaction().add(R.id.principal, new Home()).addToBackStack(null).commit();

        /*asociarControles();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.Principal, new Login()).addToBackStack(null).commit();
                    /*Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    //setContentView(R.layout.login);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(isInternet()==true){
            btn.setVisibility(View.VISIBLE);
        }else{
            btn2.setVisibility(View.VISIBLE);
        }*/
    }

    /*private void asociarControles() {
        btn = (Button)findViewById(R.id.btnLogin);
        btn2 = (Button)findViewById(R.id.btnInvitado);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
    }*/

    public boolean isInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }



}
