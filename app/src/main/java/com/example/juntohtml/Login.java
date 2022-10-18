package com.example.juntohtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {

    private Button buttonRegistro;
    private Button buttonAcceder;
    private Button buttonOlvide;
    EditText usr;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usr = findViewById(R.id.usuarioId);
        pass = findViewById(R.id.contraseñaId);

        Button botonA = findViewById(R.id.buttonAcceder);
        botonA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String mensaje = "";

                if("".equals(String.valueOf(usr.getText())) || "".equals(String.valueOf(pass.getText()))){
                    Toast.makeText(getApplicationContext(), "Llena todos los campos", Toast.LENGTH_LONG).show();
                } else{
                    try {

                        digest digest = new digest();
                        byte[] txtByte = digest.createSha1(usr.getText().toString() + pass.getText().toString());
                        String Sha1Password1 = digest.bytesToHex(txtByte);

                        json json = new json();

                        boolean BucleArchivo = true;
                        int x = 1;
                        while (BucleArchivo) {
                            File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                            if(Cfile.exists()) {
                                BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                                String lineaTexto = file.readLine();
                                file.close();

                                Myinfo datos = json.leerJson(lineaTexto);
                                String Sha1Password2 = datos.getContraseñaId();

                                if (Sha1Password1.equals(Sha1Password2)) {
                                    mensaje = "Acceso autorizado ";
                                    BucleArchivo = false;
                                } else {
                                    x = x + 1;
                                }
                            }else{
                                mensaje = "Usuario no Encontrado";
                                BucleArchivo = false;
                            }
                        }

                        if("Acceso autorizado ".equals(mensaje)){
                            Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Jungle1.class);
                            startActivity(intent);
                        }

                        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        mensaje = "Error";
                        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        buttonRegistro = findViewById(R.id.buttonRegistro);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this, Registro.class));
            }
        });
        buttonOlvide = findViewById(R.id.buttonOlvide);
        buttonOlvide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this, Jungle2.class));
            }
        });
    }
}
