package com.example.juntohtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class json extends AppCompatActivity {
    public static Myinfo leerJson(String textoJson){
        Gson gson = new Gson();
        Myinfo datos = gson.fromJson(textoJson, Myinfo.class);

        return datos;
    }

    public String leerArchivo(String Sha1Password1, boolean Cfile, int x) {
        try {

            if(Cfile) {
                BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                String lineaTexto = file.readLine();
                file.close();

                json json = new json();
                Myinfo datos = json.leerJson(lineaTexto);
                String Sha1Password2 = datos.getContraseñaId();

                if (Sha1Password1.equals(Sha1Password2)) {
                    return "Usuario Encontrado";
                } else {
                    return "Siguiente";
                }
            }else{
                return "Usuario no Encontrado";
            }

        } catch (Exception e) {
            return "Error en el Archivo";
        }
    }
}

