package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class AllCards extends AppCompatActivity implements CardRecyclerAdapter.CardListener, DbManager.DbListener, View.OnClickListener {
    DbManager manager;
    RecyclerView recyclerView;
    CardRecyclerAdapter cardRecyclerAdapter;
    List<Flashcard> cards;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_view;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cards);

        nav_view = findViewById(R.id.nav_view_cards);
        toolbar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawerLayout_cards);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nav_view.getMenu().findItem(R.id.toAllCards).setChecked(true);
        setTitle("All Flashcards");
        intent = new Intent(this, MainActivity.class);

        manager = ((AppManager)getApplication()).db;
        manager.getDatabase(this);
        manager.listener = this;
        cards = ((AppManager)getApplication()).cards;
        manager.getAllCards();
        cardRecyclerAdapter = new CardRecyclerAdapter(cards, this);
        cardRecyclerAdapter.listener = this;
        recyclerView = findViewById(R.id.cardListRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(cardRecyclerAdapter);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.toAllSets) {
                    nav_view.getMenu().findItem(R.id.toAllCards).setChecked(false);
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
    public void cardClicked(Flashcard card) {

    }

    @Override
    public void longCardClicked(Flashcard card) {
        manager.getAllClones(card.term, card.description);
    }

    @Override
    public void added(String setName) {

    }

    @Override
    public void addedCard(String cardTerm) {

    }

    @Override
    public void setListReady(List<Sets> setList) {

    }

    @Override
    public void flashcardListReady(List<Flashcard> cardList) {
        cards = cardList;
        ((AppManager)getApplication()).cards = (ArrayList<Flashcard>)cards;
        cardRecyclerAdapter.cardList = new ArrayList<>(cardList);
        cardRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void deletedSet(String setName) {

    }

    @Override
    public void cloneCheck(int totalAmt, String term, String definition, long setId, String setName) {

    }

    @Override
    public void setUpdated(String setName) {

    }

    @Override
    public void setReady(Sets set) {

    }

    @Override
    public void deleteSet(Sets set) {

    }

    @Override
    public void bye() {

    }

    @Override
    public void getSet(Sets set) {

    }

    @Override
    public void cloneCardsReady(List<Flashcard> cards) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] setNames = new String[cards.size()];
        for(int i = 0; i < cards.size(); i++){
            setNames[i] = cards.get(i).setName;
        }
        Toast.makeText(getApplicationContext(), setNames[0], Toast.LENGTH_SHORT).show();
        if(cards.size() > 1){
            ArrayList<Long> selectedItems = new ArrayList<>();
            builder.setMultiChoiceItems(setNames, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean isSelected) {
                    if(isSelected){
                        selectedItems.add(cards.get(i).cardId);
                    }
                    else if(selectedItems.contains(cards.get(i).cardId)){
                        selectedItems.remove(cards.get(i).cardId);
                    }
                }
            }).setPositiveButton("Delete", (dialogInterface, i) -> {
                if(selectedItems.isEmpty()){
                    Toast.makeText(getApplicationContext(), "No cards were deleted.", Toast.LENGTH_SHORT).show();
                }
                else{
                    manager.deleteCards(selectedItems);
                    dialogInterface.dismiss();
                }
            }).setNegativeButton("Cancel", ((dialogInterface, i) -> {
                dialogInterface.dismiss();
            })).setTitle("Delete card from sets").setCancelable(false);
        }
        else{
            builder.setTitle("Delete Flashcard?")
                    .setMessage("Are you sure you want to delete this card?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        manager.deleteCard(cards.get(0));
                        dialogInterface.dismiss();
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void deleteCard(String term) {
        Toast.makeText(getApplicationContext(), term + " has been deleted.", Toast.LENGTH_SHORT).show();
        manager.getAllCards();
    }

    @Override
    public void onClick(View view) {

    }
}