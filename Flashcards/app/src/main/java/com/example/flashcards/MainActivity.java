package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Flashcard> cards;
    EditText flexibleEdit;
    Button cardbutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cards = ((AppManager) getApplication()).cards;
        cardbutt = findViewById(R.id.button);
        cardbutt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = alertBuilder(R.layout.dialog_newset, R.string.finish, R.string.cancel, false, R.string.finish);
        LayoutInflater inflater = LayoutInflater.from(builder.getContext());
        View v = inflater.inflate(R.layout.dialog_newset, null, false);
        flexibleEdit = (EditText)v.findViewById(R.id.SetName);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flexibleEdit.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Please enter a set name", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                        }
                    }
                });
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }

    public AlertDialog.Builder alertBuilder(int layout, int positiveButton, int negativeButton, boolean cancel, int title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Context context1 = builder.getContext();
        LayoutInflater inflater = LayoutInflater.from(context1);
        View view = inflater.inflate(layout, null, false);
        flexibleEdit = (EditText)view.findViewById(R.id.SetName);

        builder.setView(inflater.inflate(layout, null))
                .setPositiveButton(positiveButton, null)
                .setNegativeButton(negativeButton, null)
                .setCancelable(cancel)
                .setTitle(title);

        return builder;
    }

}