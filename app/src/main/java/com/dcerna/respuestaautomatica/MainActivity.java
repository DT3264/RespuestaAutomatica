package com.dcerna.respuestaautomatica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void iniciaServicioBroadcast(){
        Intent intent = new Intent(this, Servicio.class);
        startService(intent);
    }
}