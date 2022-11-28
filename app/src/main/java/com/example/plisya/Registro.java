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

public class Registro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void Registrarse (View v){

        EditText Nombre = (EditText) findViewById(R.id.nombreId);
        EditText fecha = (EditText) findViewById(R.id.nacimientoId);
        EditText artista = (EditText) findViewById(R.id.artFav);
        EditText usuario = (EditText) findViewById(R.id.usuarioId);
        EditText Mail = (EditText) findViewById(R.id.correoId);
        EditText edad = (EditText) findViewById(R.id.edad);
        EditText boleta = (EditText) findViewById(R.id.boletaId);
        RadioButton Hombre = (RadioButton) findViewById(R.id.hombre);
        RadioButton Mujer = (RadioButton) findViewById(R.id.mujer);
        RadioButton Gato = (RadioButton) findViewById(R.id.gato);
        RadioButton Perro = (RadioButton) findViewById(R.id.perro);
        EditText Pswd = (EditText) findViewById(R.id.contraseñaId);

        String mensaje = "";

        if("".equals(Nombre.getText().toString()) || "".equals(fecha.getText().toString()) ||
                "".equals(artista.getText().toString()) || "".equals(usuario.getText().toString()) ||
                "".equals(Mail.getText().toString()) || "".equals(edad.getText().toString()) ||
                false == Mujer.isChecked() & false == Hombre.isChecked() ||
                false == Gato.isChecked() & false == Perro.isChecked() ||
                "".equals(boleta.getText().toString()) || "".equals(Pswd.getText().toString()))
        {
            mensaje = "Falta un Parametro";
        }
        else {
            boolean TCorreo = false;
            String Correo = "";
            for(int x = 0 ; x < Mail.length(); x++){
                if(Mail.getText().charAt(x) == '@'){
                    for(int y = x ; y < Mail.length(); y++){
                        Correo = Correo + Mail.getText().charAt(y);
                    }
                    if("@gmail.com".equals(Correo) || "@hotmail.com".equals(Correo) || "@outlook.com".equals(Correo)){
                        TCorreo = true;
                    }
                    break;
                }
            }
            if(Nombre.length() > 22 || fecha.length() > 10 || artista.length() > 30 || usuario.length() > 20 ||
                    TCorreo == false || Mail.length() > 25 || edad.length() > 3 || boleta.length() != 10 || Pswd.length() > 15){
                mensaje = "Insertar correctamente los datos";
                if(Nombre.length() > 22){mensaje = "¡Ingresa un nombre más corto!";}
                if(fecha.length() > 10){mensaje = "¡Ingresa una fecha más corta!";}
                if(artista.length() > 30){mensaje = "¡Ingresa un nombre más corto!";}
                if(usuario.length() > 20){mensaje = "¡Ingresa un usuario más corto!";}
                if(TCorreo == false){mensaje = "¡Ingresa un correo válido!";}
                if(edad.length() > 3){mensaje = "¡Ingresa un edad válida!";}
                if(boleta.length() != 10){mensaje = "¡Ingresa un número válido!";}
                if(Pswd.length() > 15){mensaje = "¡Ingresa una contraseña más corto!";}
            }else{
                try {

                    Sha1 digest = new Sha1();
                    byte[] txtByte = digest.createSha1(usuario.getText().toString() + Pswd.getText().toString());
                    String Sha1Pswd = digest.bytesToHex(txtByte);

                    String JNombre = Nombre.getText().toString();
                    String JFecha = fecha.getText().toString();
                    String JArtista = artista.getText().toString();
                    String JUsuario = usuario.getText().toString();
                    String JMail = Mail.getText().toString();
                    int JEdad = Integer.parseInt(edad.getText().toString());
                    int JBoleta = Integer.parseInt(boleta.getText().toString());
                    boolean JMujer = Mujer.isChecked();
                    boolean JGato = Gato.isChecked();
                    String JPswd = Sha1Pswd;

                    JSON json = new JSON();
                    String textoJson = json.crearJson(JNombre, JFecha, JArtista, JUsuario, JMail,
                            JEdad, JBoleta, JMujer, JGato, JPswd);

                    boolean BucleArchivo = true;
                    int x = 1;
                    while (BucleArchivo) {
                        File Cfile = new File(getApplicationContext().getFilesDir() + "/" + "Archivo" + x + ".txt");
                        if (Cfile.exists()) {
                            BufferedReader file = new BufferedReader(new InputStreamReader(openFileInput("Archivo" + x + ".txt")));
                            String lineaTexto = file.readLine();
                            String completoTexto = "";
                            while(lineaTexto != null){
                                completoTexto = completoTexto + lineaTexto;
                                lineaTexto = file.readLine();
                            }
                            file.close();

                            MyInfo datos = json.leerJson(completoTexto);
                            String ValoruserName2 = datos.getUsuario();
                            String ValorMail2 = datos.getMail();
                            int ValorNumber2 = datos.getBoletar();

                            if (JUsuario.equals(ValoruserName2) || JMail.equals(ValorMail2) || JBoleta == ValorNumber2) {
                                if(JMail.equals(ValorMail2)){mensaje = "El correo ya está registrado";}
                                if(JBoleta == ValorNumber2){mensaje = "La boleta ya está registrada";}
                                if(JUsuario.equals(ValoruserName2)){mensaje = "El usuario ya está registrado";}
                                BucleArchivo = false;
                            } else {
                                x = x + 1;
                            }
                        } else {
                            BufferedWriter file = new BufferedWriter(new OutputStreamWriter(openFileOutput("Archivo" + x + ".txt", Context.MODE_PRIVATE)));
                            file.write(textoJson);
                            file.close();
                            mensaje = "Se completó el registro";
                            Nombre.setText("");
                            fecha.setText("");
                            artista.setText("");
                            usuario.setText("");
                            Mail.setText("");
                            edad.setText("");
                            boleta.setText("");
                            Mujer.setChecked(false);
                            Hombre.setChecked(false);
                            Gato.setChecked(false);
                            Perro.setChecked(false);
                            Pswd.setText("");
                            BucleArchivo = false;
                        }
                    }

                } catch (Exception e) {
                    mensaje = "No se pudo completar el registro";
                }
            }
        }
        Toast.makeText(Registro.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void Volver (View v){
        Intent intent = new Intent (Registro.this, Login.class);
        startActivity( intent );
    }
}