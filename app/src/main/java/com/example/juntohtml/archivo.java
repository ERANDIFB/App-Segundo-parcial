package com.example.juntohtml;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class archivo extends AppCompatActivity {

    public static String datillos;

    public void CrearArchivo(String json){
        try {
            String ruta = "/data/data/com.example.juntohtml/files/Datos.txt";
            File archivo = new File(ruta);

            if (!archivo.exists()) {
                archivo.createNewFile();
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                out.println(json);
            } else {
                FileWriter fw = new FileWriter(archivo, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw);
                out.println(json);
            }
        } catch(Exception e){
            Toast.makeText( getApplicationContext(), "Error :(", Toast.LENGTH_LONG);
        }
    }

    public void LeerArchivo( String archivo ) throws FileNotFoundException, IOException {
        String cadeniux;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadeniux = b.readLine()) != null){
            datillos = datillos + cadeniux;
        }
    }
}

