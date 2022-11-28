package com.example.plisya;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler( ).postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent intent = new Intent( MainActivity.this, Login.class);
                startActivity( intent );
            }
        } , 4000 );
    }


}