package com.example.kevin.pixelprueba.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kevin.pixelprueba.Clases.HomeActivity;
import com.example.kevin.pixelprueba.Clases.MainActivity;
import com.example.kevin.pixelprueba.R;

public class Inicio extends Fragment {

    Button btn, btn2, btnEntrar;
    MainActivity actividad = new MainActivity();



    public Inicio() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        btn = (Button)view.findViewById(R.id.btnLogin);
        btn2 = (Button)view.findViewById(R.id.btnInvitado);

        if(actividad.isInternet(getContext())==true){
            btn.setVisibility(View.VISIBLE);
        }else{
            btn.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.contenedor, new Login()).addToBackStack(null).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.Principal, new Login()).addToBackStack(null).commit();
                    /*Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();*/
                    //setContentView(R.layout.login);
            }
        });



        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
