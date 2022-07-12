package com.example.flashcards;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"id"}, unique = true)})
public class Categories {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int colour;

}
