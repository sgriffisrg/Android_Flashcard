package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SetDialogFragment.DialogClickListener, Create_Cards.CardDialogClickListener, SetRecyclerAdapter.SetListener, DbManager.DbListener{
    ArrayList<Sets> sets;
    RecyclerView setView;
    Sets set;
    Button cardbutt;
    SetRecyclerAdapter recyclerAdapter;
    DbManager manager;

    int amt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = ((AppManager)getApplication()).db;
        manager.getDatabase(this);
        manager.listener = this;
        sets = ((AppManager) getApplication()).sets;
        cardbutt = findViewById(R.id.button);
        cardbutt.setOnClickListener(this);
        manager.getAllSets();
        recyclerAdapter = new SetRecyclerAdapter(sets,this);
        recyclerAdapter.listener = this;
        setView = findViewById(R.id.setRecyclerView);
        setView.setLayoutManager(new LinearLayoutManager(this));
        setView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onClick(View view) {
        SetDialogFragment setDialog = SetDialogFragment.newInstance();
        setDialog.show(getSupportFragmentManager(), SetDialogFragment.Tag);
        setDialog.listener = this;
    }

    @Override
    public void dialogListenerCreateSet(String setName) {
        set = new Sets(setName);
        Create_Cards cardDialog = Create_Cards.newInstance(setName, set);
        cardDialog.show(getSupportFragmentManager(), Create_Cards.Tag);
        cardDialog.listener = this;
    }

    @Override
    public void dialogListenerAddCard(String term, String description, long setId) {
        manager.addNewCard(new Flashcard(term, description, setId));
    }

    @Override
    public void setDialogListenerCancel() {

    }

    @Override
    public void cardDialogListenerCancel(Sets set) {
        manager.addNewSet(set);
        manager.getAllSets();
    }

    @Override
    public void setClicked() {
    }

    @Override
    public void added(String setName) {
    }

    @Override
    public void addedCard(String cardTerm) {

    }

    @Override
    public void setListReady(List<Sets> setList) {
        sets = (ArrayList<Sets>) setList;
        ((AppManager)getApplication()).sets = sets;
        recyclerAdapter.setList = new ArrayList<>(setList);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void flashcardListReady(List<Flashcard> cardList) {

    }

    @Override
    public void deleted(String name) {

    }

    @Override
    public void setUpdated(String setName) {

    }

    @Override
    public void setReady(Sets set) {

    }
}