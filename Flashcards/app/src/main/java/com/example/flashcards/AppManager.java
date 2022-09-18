package com.example.flashcards;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class AppManager extends Application {
    ArrayList<Flashcard> cards = new ArrayList<>();
    List<Flashcard> cardsToBeReviewed;
    ArrayList<Sets> sets = new ArrayList<>();
    DbManager db = new DbManager();
    int position = 0;
    public AppManager(){

    }
}
