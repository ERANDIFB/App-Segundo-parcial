package com.example.juntohtml;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Registro extends AppCompatActivity {
    EditText nombreId, usuarioId, contraseñaId, emailId;
    private Button Finalizar;
    byte[] byt = null;
    String ContrasenaD;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreId = (EditText) (findViewById(R.id.nombreId));
        usuarioId = (EditText) (findViewById(R.id.usuarioId));
        contraseñaId = (EditText) (findViewById(R.id.contraseñaId));
        emailId = (EditText) (findViewById(R.id.emailId));

    }


    public boolean validar() {
        boolean retorno = true;

        String nombre = nombreId.getText().toString();
        String usuario = usuarioId.getText().toString();
        String contraseña = contraseñaId.getText().toString();
        String email = emailId.getText().toString();

        if (nombre.isEmpty()) {
            nombreId.setError("Campo obligatorio");
            retorno = false;
        }
        if (usuario.isEmpty()) {
            usuarioId.setError("Campo obligatorio");
            retorno = false;
        }
        if (contraseña.isEmpty()) {
            contraseñaId.setError("Campo obligatorio");
            retorno = false;
        }
        if (email.isEmpty()) {
            emailId.setError("Campo obligatorio");
            retorno = false;
        }
        return retorno;
    }

    ;

    public void leerArchivo() {
        try {
            BufferedReader aux = new BufferedReader(new InputStreamReader(openFileInput("datos.txt")));

            String texto = aux.readLine();

            Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("Archivo", "ERROR AL LEER");
        }
    }

    public void Finalizar(View v) throws IOException {
        usuarioId = findViewById(R.id.usuarioId);
        nombreId = findViewById(R.id.nombreId);
        contraseñaId = findViewById(R.id.contraseñaId);
        emailId = findViewById(R.id.emailId);
        String cadena = usuarioId.getText().toString() + contraseñaId.getText().toString();
        digest digest = new digest();
        byt = digest.createSha1(cadena);
        ContrasenaD = digest.bytesToHex(byt);
        if (validar()) {
            Toast.makeText(this, "Ingresó datos", Toast.LENGTH_SHORT).show();
        }
        String VUsuario = String.valueOf(usuarioId.getText());
        String VNombre = String.valueOf(nombreId.getText());
        String VEmail = String.valueOf(emailId.getText());
        Myinfo myInfo = null;
        Gson gson = null;
        String json = null;
        String mensaje = null;
        myInfo = new Myinfo();
        myInfo.setUsuarioId(VUsuario);
        myInfo.setNombreId(VNombre);
        myInfo.setContraseñaId(ContrasenaD);
        myInfo.setEmailId(VEmail);
        Log.d(TAG, "TEST");
        gson = new Gson();
        json = gson.toJson(myInfo);
        if (json != null) {
            Log.d(TAG, json);
            mensaje = "OK";
        } else {
            mensaje = "ERROR";
        }
        try {
            json json2 = new json();
            boolean BucleArchivo = true;
            int x = 1;
            while (BucleArchivo) {
                File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                if (Cfile.exists()) {
                    BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                    String lineaTexto = file.readLine();
                    file.close();

                    Myinfo datos = json2.leerJson(lineaTexto);
                    String ValorUsr2 = datos.getUsuarioId();

                    if (VUsuario.equals(ValorUsr2)) {
                        mensaje = "El usuario ya existe";
                        BucleArchivo = false;
                    } else {
                        x = x + 1;
                    }
                } else {
                    BufferedWriter file = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + x + ".txt", Context.MODE_PRIVATE)));
                    file.write(json);
                    file.close();
                    mensaje = "¡Registro exitoso!";
                    BucleArchivo = false;
                }
            }
        } finally {

        }
    }
}



