package com.example.flashcards;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;

public class DialogFactory {
    public static AlertDialog.Builder alertBuilder(int layout, int positiveButton, int negativeButton, boolean cancel, int title, Activity context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Context context1 = builder.getContext();
        LayoutInflater inflater = LayoutInflater.from(context1);

        builder.setView(inflater.inflate(layout, null))
                .setPositiveButton(positiveButton, null)
                .setNegativeButton(negativeButton, null)
                .setCancelable(cancel)
                .setTitle(title);

        return builder;
    }
}
