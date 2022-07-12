package com.example.flashcards;

import android.app.Application;

import java.util.ArrayList;

public class AppManager extends Application {
    ArrayList<Flashcard> cards = new ArrayList<Flashcard>();

    public AppManager(){

    }
}
