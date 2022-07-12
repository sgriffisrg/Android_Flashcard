package com.example.flashcards;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity(indices = {@Index(value = {"cardId"}, unique = true)})
public class Flashcard {
    public Flashcard(String term, String description, int colour){
        this.term = term;
        this.description = description;
        this.colour = colour;
    }
    @PrimaryKey(autoGenerate = true)
    public long cardId;
    public String term;
    public String description;
    public int colour;
}
