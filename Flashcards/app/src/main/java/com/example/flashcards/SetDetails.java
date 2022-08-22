package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class SetDetails extends AppCompatActivity implements Create_Cards.CardDialogClickListener, CardRecyclerAdapter.CardListener, DbManager.DbListener, View.OnClickListener {
    RecyclerView cardList;
    CardRecyclerAdapter cardRecyclerAdapter;
    List<Flashcard> cards;
    DbManager manager;
    long setId;
    Sets set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_details);


        setId = getIntent().getLongExtra("setId", 0);

        manager = ((AppManager)getApplication()).db;
        manager.getDatabase(this);
        manager.listener = this;
        cards = ((AppManager)getApplication()).cards;
        manager.getCardsInSet(setId);
        cardList = findViewById(R.id.setCardsRecycler);
        cardRecyclerAdapter = new CardRecyclerAdapter(cards, this);
        cardRecyclerAdapter.listener = this;
        cardList.setLayoutManager(new GridLayoutManager(this, 2));
        cardList.setAdapter(cardRecyclerAdapter);

    }

    @Override
    public void dialogListenerAddCard(String term, String description, long setId, String setName) {

    }

    @Override
    public void cardDialogListenerCancel(Sets set) {

    }

    @Override
    public void cardClicked(Flashcard card) {

    }

    @Override
    public void longCardClicked(Flashcard card) {

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

    }

    @Override
    public void deleteCard(String term) {

    }

    @Override
    public void onClick(View view) {
        int idClicked = view.getId();

        switch (idClicked){
            case R.id.addCards:
                break;
            case R.id.reviewCardsFromSet:
                break;
        }
    }
}