package com.dcerna.respuestaautomatica;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Servicio extends Service {
    String TAG = "Servicio";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    MiBroadcast broadcast;

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        Log.d(TAG, "Servicion creado");
        broadcast = new MiBroadcast();
        registerReceiver(broadcast, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast);
    }
}
