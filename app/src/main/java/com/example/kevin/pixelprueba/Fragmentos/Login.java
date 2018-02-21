package com.example.kevin.pixelprueba.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kevin.pixelprueba.Clases.HomeActivity;
import com.example.kevin.pixelprueba.Clases.MainActivity;
import com.example.kevin.pixelprueba.Clases.SQLite;
import com.example.kevin.pixelprueba.Clases.Usuario;
import com.example.kevin.pixelprueba.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Fragment {

    Button btnEntrar;
    EditText username, password;
    String url = "http://192.168.0.22:8000/ws/acceso";
    TextView estado, invitado;
    MainActivity actividad = new MainActivity();
    LinearLayout carga;
    public static boolean pulsado = false;

    public static SQLiteDatabase dbPixel;
    private SQLiteCursor crNotas;


    public Login() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btnEntrar = (Button)view.findViewById(R.id.btnEntrar);
        username = (EditText)view.findViewById(R.id.username);
        password = (EditText)view.findViewById(R.id.password);
        estado = (TextView)view.findViewById(R.id.estado);
        invitado = (TextView)view.findViewById(R.id.invitado);
        carga =(LinearLayout)view.findViewById(R.id.loadingPanel);

        inicBD();

        invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pulsado = true;
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        Usuario user = new Usuario(username.getText().toString(), password.getText().toString());

        acceso();




        return view;
    }

    private void acceso() {


        if(actividad.isInternet(getContext())==true){
            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    estado.setVisibility(View.GONE);
                    carga.setVisibility(View.VISIBLE);
                    if(!username.getText().toString().equals("") && !password.getText().toString().equals("")){



                        JSONObject admin = new JSONObject();

                        try {
                            admin.put("username", username.getText().toString());
                            admin.put("password", password.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(actividad, "error en el objeto", Toast.LENGTH_SHORT).show();
                        }

                            JsonObjectRequest jsonPeticion;
                            jsonPeticion = new JsonObjectRequest(Request.Method.POST, url, admin, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                            try {
                                //Integer conectado = Integer.parseInt(response.getString("acceso"));
                                String conectado = response.getString("acceso");

                                if(conectado.equals("1")){

                                    Toast.makeText(getContext(), "Bienvenido " + username.getText(), Toast.LENGTH_LONG).show();

                                    carga.setVisibility(View.GONE);
                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();

                                } else{
                                    acceso();
                                    carga.setVisibility(View.GONE);
                                    estado.setText("Acceso incorrecto.");
                                    estado.setVisibility(View.VISIBLE);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            acceso();
                            Log.d("volley_error",error.toString());

                            carga.setVisibility(View.GONE);
                            estado.setText("Acceso incorrecto.");
                            estado.setVisibility(View.VISIBLE);
                        }
                    });

                    Volley.newRequestQueue(getContext()).add(jsonPeticion);
                    } else{
                        acceso();
                        carga.setVisibility(View.GONE);
                        estado.setText("Es obligatorio rellenar los campos.");
                        estado.setVisibility(View.VISIBLE);
                    }
                }
            });
        }else{
            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    acceso();
                    estado.setText("No hay conexión a la red.");
                    estado.setVisibility(View.VISIBLE);

                }
            });

        }
    }

    public void inicBD() {
        SQLite db = new SQLite(getContext(), "pixelGames.db", null,1);
        dbPixel = db.getReadableDatabase();
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
