package com.example.flashcards;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.ArrayList;
@Entity(indices = {@Index(value = {"setId", "name"}, unique = true)})
public class Sets{
    @PrimaryKey(autoGenerate = true)
    public long setId;
    public String name;
    public int cardAmt = 0;

    public Sets(String name){
        this.name = name;
    }

    public void countIncrease(){
        cardAmt++;
    }
}
