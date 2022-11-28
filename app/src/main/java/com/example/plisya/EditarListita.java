package com.example.plisya;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EditarListita extends AppCompatActivity {

    private EditText Name, Password;
    private RadioButton Opcion1, Opcion2, Opcion3, Opcion4;
    private int []imagenUser = { R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_listita);
        Name = (EditText) findViewById(R.id.nombreId);
        Password = (EditText) findViewById(R.id.contraseñaId);
        Opcion1 = (RadioButton) findViewById(R.id.radioButton1);
        Opcion2 = (RadioButton) findViewById(R.id.radioButton2);
        Opcion3 = (RadioButton) findViewById(R.id.radioButton3);
        Opcion4 = (RadioButton) findViewById(R.id.radioButton4);

        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        int numContext = getIntent().getExtras().getInt("numContext");

        try {
            if (numContext == 2) {
                int numArchivoCuenta = getIntent().getExtras().getInt("numArchivoCuenta");
                BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + numArchivo + "." + numArchivoCuenta + ".txt")));
                String lineaTexto = file.readLine();
                String completoTexto = "";
                while(lineaTexto != null){
                    completoTexto = completoTexto + lineaTexto;
                    lineaTexto = file.readLine();
                }
                file.close();

                JSON json = new JSON();
                MyCuenta datos = json.leerJsonCuenta(completoTexto);
                String valorAccountName = datos.getNameCuenta();
                String valorAccountPassword = datos.getPassCuenta();
                int valorAccountImage = datos.getImage();

                Name.setText(valorAccountName);
                Password.setText(valorAccountPassword);
                if(valorAccountImage == imagenUser[0]){Opcion1.setChecked(true);}
                if(valorAccountImage == imagenUser[1]){Opcion2.setChecked(true);}
                if(valorAccountImage == imagenUser[2]){Opcion3.setChecked(true);}
                if(valorAccountImage == imagenUser[3]){Opcion4.setChecked(true);}
            }
        }catch(Exception e){}
    }

    public void Enviar (View v){
        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        int numContext = getIntent().getExtras().getInt("numContext");
        if(false == Opcion1.isChecked() & false == Opcion2.isChecked() &
                false == Opcion3.isChecked() & false == Opcion4.isChecked()) {
            Toast.makeText(EditarListita.this, "Falta un parametro", Toast.LENGTH_SHORT).show();
        }else {
            if(Name.length() > 22 || Password.length() > 30){
                String mensaje = "Parametro Erroneo";
                if(Name.length() > 22){mensaje = "Nombre Muy Largo";}
                if(Password.length() > 30){mensaje = "Contraseña Muy Larga";}
                Toast.makeText(EditarListita.this, mensaje, Toast.LENGTH_SHORT).show();
            }else {
                JSON json = new JSON();
                if (numContext == 1) {
                    try{
                        String valorNombre = Name.getText().toString();
                        String valorPassword = Password.getText().toString();
                        int valorImage = imagenUser[0];
                        if(Opcion1.isChecked()){valorImage = imagenUser[0];}
                        if(Opcion2.isChecked()){valorImage = imagenUser[1];}
                        if(Opcion3.isChecked()){valorImage = imagenUser[2];}
                        if(Opcion4.isChecked()){valorImage = imagenUser[3];}

                        String textoJsonCuenta = json.crearJsonCuenta( valorNombre, valorPassword, valorImage);

                        boolean BucleArchivo = true;
                        int x = 1;
                        while (BucleArchivo) {
                            File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + numArchivo + "." + x + ".txt");
                            if (Cfile.exists()) {x = x + 1;}
                            else {
                                BufferedWriter fileC = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + numArchivo + "." + x + ".txt", Context.MODE_PRIVATE)));
                                fileC.write(textoJsonCuenta);
                                fileC.close();

                                BucleArchivo = false;
                            }
                        }
                    }catch(Exception e){}
                }
                if (numContext == 2){
                    try{
                        int numArchivoCuenta = getIntent().getExtras().getInt("numArchivoCuenta");

                        String valorNombre = Name.getText().toString();
                        String valorPassword = Password.getText().toString();
                        int valorImage = imagenUser[0];
                        if(Opcion1.isChecked()){valorImage = imagenUser[0];}
                        if(Opcion2.isChecked()){valorImage = imagenUser[1];}
                        if(Opcion3.isChecked()){valorImage = imagenUser[2];}
                        if(Opcion4.isChecked()){valorImage = imagenUser[3];}

                        String textoJsonCuenta = json.crearJsonCuenta( valorNombre, valorPassword, valorImage);

                        BufferedWriter fileC = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + numArchivo + "." + numArchivoCuenta + ".txt", Context.MODE_PRIVATE)));
                        fileC.write(textoJsonCuenta);
                        fileC.close();
                    }catch(Exception e){}
                }
                Intent intent = new Intent(EditarListita.this, Listita.class);
                intent.putExtra("numArchivo", numArchivo);
                startActivity(intent);
            }
        }
    }

    public void Volver (View v){
        int numArchivo = getIntent().getExtras().getInt("numArchivo");
        Intent intent = new Intent (EditarListita.this, Listita.class);
        intent.putExtra("numArchivo", numArchivo);
        startActivity( intent );
    }
}