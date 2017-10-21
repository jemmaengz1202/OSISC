package com.jemma.isic3.osisc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import javax.crypto.KeyAgreement;

public class Agradecimiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
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
                i = new Intent(Agradecimiento.this, AcercaDe.class);
                startActivity(i);
                break;
            case R.id.inicio:
                i = new Intent(Agradecimiento.this, MainActivity.class);
                startActivity(i);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
