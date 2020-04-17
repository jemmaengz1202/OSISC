package com.jemma.isic3.osisc;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThread extends Thread {
    public static TextView temperatura;
    public static TextView humedad;
    public static TextView iluminacion;
    public static TextView litros;

    public static String cadena;
    static final int RECIEVE_MESSAGE = 1;

    private static InputStream mmInStream = null;
    private static OutputStream mmOutStream = null;
    private static StringBuilder sb = new StringBuilder();

    public static BluetoothSocket socket;

    public static Handler h = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case RECIEVE_MESSAGE:                                                    // if receive massage
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, msg.arg1);
                    sb.append(strIncom);												// append string
                    int endOfLineIndex = sb.indexOf("\r\n");							// determine the end-of-line
                    if (endOfLineIndex > 0) {                                            // if end-of-line,
                        String sbprint = sb.substring(0, endOfLineIndex);                // extract string
                        sb.delete(0, sb.length());

                        String stArr[] = sbprint.split(",");

                        temperatura.setText("Temperatura: " + stArr[0] + "%");
                        humedad.setText("Humedad: " + stArr[1] + "%");
                        iluminacion.setText("Iluminación: " + stArr[2] + "%");
                        litros.setText("Capacidad de agua: " + stArr[3] + "%");
                    }
                    //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
                    break;
            }
        }
    };

    public ConnectedThread() {

        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = ConnectedThread.socket.getInputStream();
            tmpOut = ConnectedThread.socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[256];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);		// Get number of bytes and message in "buffer"
                h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();		// Send to message queue Handler
            } catch (IOException e) {
                break;
            }
        }
    }
}
