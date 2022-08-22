package com.example.flashcards;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

@Entity(indices = {@Index(value = {"cardId"}, unique = true)})
public class Flashcard {
    public Flashcard(String term, String description, long setId, long clone, String setName){
        this.term = term;
        this.description = description;
        this.setOwnedId = setId;
        this.clone = clone;
        this.setName = setName;
    }
    public Flashcard(){}
    @PrimaryKey(autoGenerate = true)
    public long cardId;
    public long setOwnedId;
    public String term;
    public String description;
    public String setName;
    public int colour;
    public long clone = 0;
}
