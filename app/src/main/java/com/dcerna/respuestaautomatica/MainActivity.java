package com.dcerna.respuestaautomatica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txtNumero, txtMensaje;
    TextView txtnewNumero, txtnewMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciaServicioBroadcast();

        txtNumero = findViewById(R.id.txtNumero);
        txtnewNumero = findViewById(R.id.lbNumero);
        txtMensaje = findViewById(R.id.txtMessage);
        txtnewMensaje = findViewById(R.id.lbMensaje);

        SharedPreferences sharedPreferences = this.getSharedPreferences("Datos",MODE_PRIVATE);
        String num = sharedPreferences.getString("NUMERO","");
        String message = sharedPreferences.getString("MENSAJE","");
        txtnewNumero.setText(num);
        txtnewMensaje.setText(message);

        findViewById(R.id.btnAgregar).setOnClickListener(
                view -> {
                    String newNumero = txtNumero.getText().toString();
                    SharedPreferences sh = this.getSharedPreferences("Datos", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sh.edit();
                    myEdit.putString("NUMERO", newNumero);
                    myEdit.apply();
                    txtnewNumero.setText(newNumero);
                }
        );
        findViewById(R.id.btnAgregarMensaje).setOnClickListener(
                view -> {
                    String nMessage = txtMensaje.getText().toString();
                    SharedPreferences sh = this.getSharedPreferences("Datos", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sh.edit();
                    myEdit.putString("MENSAJE", nMessage);
                    myEdit.apply();
                    txtnewMensaje.setText(nMessage);
                }
        );
    }

    void iniciaServicioBroadcast(){
        Intent intent = new Intent(this, Servicio.class);
        startService(intent);
    }
}