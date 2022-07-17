package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SetDialogFragment.DialogClickListener {
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
        SetDialogFragment setDialog = SetDialogFragment.newInstance();
        setDialog.show(getSupportFragmentManager(), SetDialogFragment.Tag);
        setDialog.listener = this;
    }

    @Override
    public void dialogListenerCreateSet(String setName) {
        ((AppManager)getApplication()).firstSet = new Sets(setName);
    }

    @Override
    public void dialogListenerCancel() {

    }
}