package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SetDialogFragment.DialogClickListener, Create_Cards.CardDialogClickListener, SetRecyclerAdapter.SetListener, DbManager.DbListener{
    ArrayList<Sets> sets;
    RecyclerView setView;
    Sets set;
    FloatingActionButton addSet;
    SetRecyclerAdapter recyclerAdapter;
    DbManager manager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_view;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setExitTransition(new Slide());
        getWindow().setEnterTransition(new Explode());
        nav_view = findViewById(R.id.nav_view);
        manager = ((AppManager)getApplication()).db;
        manager.getDatabase(this);
        manager.listener = this;
        addSet = findViewById(R.id.addSet);
        addSet.setOnClickListener(this);
        sets = ((AppManager) getApplication()).sets;
        manager.getAllSets();
        recyclerAdapter = new SetRecyclerAdapter(sets,this);
        recyclerAdapter.listener = this;
        setView = findViewById(R.id.setRecyclerView);
        setView.setLayoutManager(new LinearLayoutManager(this));
        setView.setAdapter(recyclerAdapter);
        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nav_view.getMenu().findItem(R.id.toAllSets).setChecked(true);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Set List");
        intent = new Intent(this, AllCards.class);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.toAllCards) {
                    nav_view.getMenu().findItem(R.id.toAllSets).setChecked(false);
                    drawerLayout.close();
                    startActivity(intent);
                    return true;
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            if(drawerLayout.isOpen())
                drawerLayout.close();
            else
                drawerLayout.open();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        SetDialogFragment setDialog = SetDialogFragment.newInstance();
        setDialog.show(getSupportFragmentManager(), SetDialogFragment.Tag);
        setDialog.listener = this;
    }

    @Override
    public void dialogListenerCreateSet(String setName, String description) {
        set = new Sets(setName, description);
        manager.addNewSet(set);
    }

    @Override
    public void dialogListenerAddCard(String term, String description, long setId, String setName) {
        manager.checkForClones(term, description, setId, setName);
    }

    @Override
    public void setDialogListenerCancel() {

    }

    @Override
    public void cardDialogListenerCancel(Sets set) {
        manager.updateSet(set);
    }

    @Override
    public void setClicked(Sets set) {
        Intent intent = new Intent(this, SetDetails.class);
        intent.putExtra("setId", set.setId);

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public void longSetClicked(Sets set) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Set?")
                .setMessage("Do you want to delete this set?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        manager.deleteCardsInSet(set.setId, set);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void added(String setName) {
        manager.getOneSet(setName);
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
    public void deletedSet(String setName) {
        Toast.makeText(getApplicationContext(), setName + " has been deleted.", Toast.LENGTH_SHORT).show();
        manager.getAllSets();
    }

    @Override
    public void cloneCheck(int totalAmt, String term, String definition, long setId, String setName) {
        manager.addNewCard(new Flashcard(term, definition, setId, totalAmt, setName));
    }

    @Override
    public void setUpdated(String setName) {
        manager.getAllSets();
    }

    @Override
    public void setReady(Sets set) {

    }

    @Override
    public void deleteSet(Sets set) {
        manager.deleteSet(set);
    }

    @Override
    public void bye() {
        Toast.makeText(getApplicationContext(), "bye", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSet(Sets set) {
        Toast.makeText(getApplicationContext(), set.name, Toast.LENGTH_SHORT).show();
        Create_Cards cardDialog = Create_Cards.newInstance(set.name, set);
        cardDialog.show(getSupportFragmentManager(), Create_Cards.Tag);
        cardDialog.listener = this;
    }

    @Override
    public void cloneCardsReady(List<Flashcard> cards) {

    }

    @Override
    public void deleteCard(String term) {

    }


}