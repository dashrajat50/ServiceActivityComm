package com.example.servicebroadcastsample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;

public class ServiceActivity extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service is Started ", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service is Stopped. Thank u", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
       // Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Service is created " + ("\ud83d\ude01"),Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
