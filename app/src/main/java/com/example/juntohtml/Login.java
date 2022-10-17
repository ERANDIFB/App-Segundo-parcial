package com.example.juntohtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class Login extends AppCompatActivity {

    private Button buttonRegistro;
    private Button buttonAcceder;
    private Button buttonOlvide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonAcceder = findViewById(R.id.buttonAcceder);
        buttonAcceder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this, Jungle1.class));
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
