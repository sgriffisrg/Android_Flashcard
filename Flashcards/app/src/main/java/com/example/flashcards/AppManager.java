package com.example.flashcards;

import android.app.Application;

import java.util.ArrayList;

public class AppManager extends Application {
    ArrayList<Flashcard> cards = new ArrayList<>();
    ArrayList<Sets> sets = new ArrayList<>();
    DbManager db = new DbManager();
    public AppManager(){

    }
}
