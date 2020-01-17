package com.example.myassignmentid.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utilities {

    // display an alert dialog with user requested title and message
    public static void showAlertDialog(Context context, String title, String message) {
        AlertDialog alertDialog = null;
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
