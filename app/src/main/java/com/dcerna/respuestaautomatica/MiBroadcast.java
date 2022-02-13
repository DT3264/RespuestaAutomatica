package com.dcerna.respuestaautomatica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MiBroadcast extends BroadcastReceiver {
    String TAG = "MiBroadcast";

    String numero = "";
    String ultimoEstado = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Broadcast recibido");
        if(intent.hasExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)){
            numero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        }
        ultimoEstado = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        compruebaEstado(context);
    }

    private void compruebaEstado(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Datos", Context.MODE_PRIVATE);
        String numeroRegistrado = sharedPreferences.getString("NUMERO", "");
        String mensaje = sharedPreferences.getString("MENSAJE", "");
        if(ultimoEstado.equals("IDLE")
                && numero.length()>0
                && numero.equals(numeroRegistrado)) {
            enviaMensaje(numeroRegistrado, mensaje);
            numero = "";
        }
    }

    private void enviaMensaje(String destino, String mensaje) {
        SmsManager smsManager =  SmsManager.getDefault();
        smsManager.sendTextMessage(destino, "", mensaje, null, null);
        Log.d(TAG, "Mensaje enviado");
    }
}
