package com.example.plisya;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Acceso extends AppCompatActivity {

        TextView textview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_acceso);
            textview = findViewById(R.id.textView);

            try {

                int numArchivo = getIntent().getExtras().getInt("numArchivo");
                JSON json = new JSON();

                BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + ".txt")));
                String lineaTexto = file.readLine();
                String completoTexto = "";
                while(lineaTexto != null){
                    completoTexto = completoTexto + lineaTexto;
                    lineaTexto = file.readLine();
                }
                MyInfo datos = json.leerJson(completoTexto);
                file.close();

                textview.setText("¡Bienvenido! " + datos.getNombre());
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
                new Handler( ).postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        Intent intent = new Intent( Acceso.this, Listita.class);
                        intent.putExtra("numArchivo", numArchivo);
                        startActivity( intent );
                    }
                } , 4000 );
            }catch(Exception e){}
        }
    }