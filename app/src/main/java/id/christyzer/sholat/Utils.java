package id.christyzer.sholat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import id.christyzer.sholat.api.ApiClient;
import id.christyzer.sholat.api.api;

public class Utils {
    public static api getApi() {
        return ApiClient.getApi().create(api.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
        return alertDialog;
    }
}
