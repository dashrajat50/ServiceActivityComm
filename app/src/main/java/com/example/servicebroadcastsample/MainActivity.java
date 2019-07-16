package com.example.servicebroadcastsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviceactivitycomm.R;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;
    private Button btn, btnStart, btnStop;
    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnShow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt = findViewById(R.id.txtMsg);
                Intent intent = new Intent();
                intent.putExtra("MSG", edt.getText().toString());
                intent.setAction("com.tutlane.broadcast.MY_NOTIFICATION");
                sendBroadcast(intent);
            }
        });
        btnStart = findViewById(R.id.buttonStart);
        btnStop = findViewById(R.id.buttonStop);
        // start service
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startService(intent);
            }
        });
        // stop service
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //1. first register the broadcast receiver .
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.tutlane.broadcast.MY_NOTIFICATION");
        registerReceiver(myReceiver, intentFilter);
    }

    // 2. Broadcast receiver
    public static class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getExtras().getString("MSG");
            Toast.makeText(context, "Received :" + data, Toast.LENGTH_SHORT).show();
        }
    }
}
