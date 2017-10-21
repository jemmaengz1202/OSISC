package com.jemma.isic3.osisc;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
//import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Control extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout durazno;
    private LinearLayout maiz;
    private LinearLayout fresa;
    private LinearLayout agave;
    private LinearLayout frijol;
    private LinearLayout tomate;
    private Button button_prueba;
    private Button button_cancelar;

    private String address = null;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADRESS);
        setContentView(R.layout.activity_control);

        durazno = (LinearLayout) findViewById(R.id.durazno);
        maiz = (LinearLayout) findViewById(R.id.maiz);
        fresa = (LinearLayout) findViewById(R.id.fresa);
        agave = (LinearLayout) findViewById(R.id.agave);
        frijol = (LinearLayout) findViewById(R.id.frijol);
        tomate = (LinearLayout) findViewById(R.id.tomate);

        button_prueba = (Button) findViewById(R.id.button_prueba);
        button_cancelar = (Button) findViewById(R.id.detener);

        new ConnectBT().execute();

        durazno.setOnTouchListener(this);
        maiz.setOnTouchListener(this);
        fresa.setOnTouchListener(this);
        frijol.setOnTouchListener(this);
        agave.setOnTouchListener(this);
        tomate.setOnTouchListener(this);

        button_prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try
                    {
                        btSocket.getOutputStream().write("P".getBytes());
                        msg("Se ha enviado una prueba");
                    }
                    catch (IOException ex)
                    {
                        msg("Error");
                    }
                }
            }
        });

        button_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try
                    {
                        btSocket.getOutputStream().write("O".getBytes());
                        msg("Se ha detenido la ejecucion de OSISC");
                    }
                    catch (IOException ex)
                    {
                        msg("Error");
                    }
                }
            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        Intent intent = new Intent(Control.this, Agradecimiento.class);
        switch(v.getId()) {
            case R.id.durazno:
                switch(e.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        durazno.setBackgroundColor(ContextCompat.getColor(this, R.color.durazno_dark));
                        break;
                    case MotionEvent.ACTION_UP:
                        durazno.setBackgroundColor(ContextCompat.getColor(this, R.color.durazno));
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("D".toString().getBytes());
                                msg("Se ha seleccionado el modo Durazno");
                                startActivity(intent);
                            }
                            catch (IOException ex)
                            {
                                msg("Error");
                            }
                        }
                        break;
                }
                break;

            case R.id.maiz:
                switch(e.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        maiz.setBackgroundColor(ContextCompat.getColor(this, R.color.maiz_dark));
                        break;
                    case MotionEvent.ACTION_UP:
                        maiz.setBackgroundColor(ContextCompat.getColor(this, R.color.maiz));
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("M".toString().getBytes());
                                msg("Se ha seleccionado el modo Maíz");
                                startActivity(intent);
                            }
                            catch (IOException ex)
                            {
                                msg("Error");
                            }
                        }
                        break;
                }
                break;

            case R.id.fresa:
                switch(e.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        fresa.setBackgroundColor(ContextCompat.getColor(this, R.color.fresa_Dark));
                        break;
                    case MotionEvent.ACTION_UP:
                        fresa.setBackgroundColor(ContextCompat.getColor(this, R.color.fresa));
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("S".toString().getBytes());
                                msg("Se ha seleccionado el modo Durazno");
                                startActivity(intent);
                            }
                            catch (IOException ex)
                            {
                                msg("Error");
                            }
                        }
                        break;
                }
                break;

            case R.id.agave:
                switch(e.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        agave.setBackgroundColor(ContextCompat.getColor(this, R.color.agave_dark));
                        break;
                    case MotionEvent.ACTION_UP:
                        agave.setBackgroundColor(ContextCompat.getColor(this, R.color.agave));
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("A".toString().getBytes());
                                msg("Se ha seleccionado el modo Agave");
                                startActivity(intent);
                            }
                            catch (IOException ex)
                            {
                                msg("Error");
                            }
                        }
                        break;
                }
                break;

            case R.id.frijol:
                switch(e.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        frijol.setBackgroundColor(ContextCompat.getColor(this, R.color.frijol_dark));
                        break;
                    case MotionEvent.ACTION_UP:
                        frijol.setBackgroundColor(ContextCompat.getColor(this, R.color.frijol));
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("F".toString().getBytes());
                                msg("Se ha seleccionado el modo Frijol");
                                startActivity(intent);
                            }
                            catch (IOException ex)
                            {
                                msg("Error");
                            }
                        }
                        break;
                }
                break;

            case R.id.tomate:
                switch(e.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        tomate.setBackgroundColor(ContextCompat.getColor(this, R.color.tomate_dark));
                        break;
                    case MotionEvent.ACTION_UP:
                        tomate.setBackgroundColor(ContextCompat.getColor(this, R.color.tomate));
                        if (btSocket!=null)
                        {
                            try
                            {
                                btSocket.getOutputStream().write("T".toString().getBytes());
                                msg("Se ha seleccionado el modo Tomate");
                                startActivity(intent);
                            }
                            catch (IOException ex)
                            {
                                msg("Error");
                            }
                        }
                        break;
                }
                break;

            default:
        }
        return false;
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(Control.this, "Conectando...", "Por favor espere!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Fallo en la conexión. ¿Se trata de un dispositivo SPP?");
                finish();
            }
            else
            {
                msg("Conectado.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ma, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent i;

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.about:
                i = new Intent(Control.this, AcercaDe.class);
                startActivity(i);
                break;
            case R.id.inicio:
                i = new Intent(Control.this, MainActivity.class);
                startActivity(i);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
