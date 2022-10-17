package com.example.juntohtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    EditText nombreId, usuarioId, contraseñaId, emailId;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreId = (EditText) (findViewById(R.id.nombreId));
        usuarioId = (EditText) (findViewById(R.id.usuarioId));
        contraseñaId = (EditText) (findViewById(R.id.contraseñaId));
        emailId = (EditText) (findViewById(R.id.emailId));

    }
    public void Finalizar (View v)
    {
        if (validar()) {
            Toast.makeText(this, "Ingresó datos", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validar ()
    {
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

}


