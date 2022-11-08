package com.example.juntohtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Jungle1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jungle1);

        TextView Nombre;

        Nombre = findViewById(R.id.NombreUsr);



        try{
            int y = getIntent().getExtras().getInt("archivo");
            json json = new json();
            BufferedReader archivito = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + y + ".txt")));
            String datos = archivito.readLine();
            Myinfo Data = json.leerJson(datos);
            archivito.close();

            Nombre.setText("Bienvenid@ a la app, " +Data.getNombreId() );
        } catch(Exception e){
        }


    }

    }
