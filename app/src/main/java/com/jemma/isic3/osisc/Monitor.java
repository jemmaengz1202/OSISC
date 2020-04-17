package com.jemma.isic3.osisc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Monitor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        ConnectedThread.temperatura = (TextView) findViewById(R.id.temperatura);
        ConnectedThread.humedad = (TextView) findViewById(R.id.humedad);
        ConnectedThread.iluminacion = (TextView) findViewById(R.id.iluminacion);
        ConnectedThread.litros = (TextView) findViewById(R.id.litros);

        ConnectedThread ct = new ConnectedThread();
        ct.run();
    }
}
