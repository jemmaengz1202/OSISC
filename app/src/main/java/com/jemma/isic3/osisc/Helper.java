package com.jemma.isic3.osisc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

/**
 * Created by Acer Aspire E 15 on 01/10/2017.
 */

public class Helper {
    private static boolean canceled = false;

    public static boolean printAlert(String title, String text, final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        // set title
        alertDialogBuilder.setTitle(title);
        // set dialog message
        alertDialogBuilder
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        canceled = true;
                    }
                });
        AlertDialog al = alertDialogBuilder.create();
        al.show();
        return canceled;
    }
}
