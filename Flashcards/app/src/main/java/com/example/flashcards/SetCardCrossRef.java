package com.example.flashcards;

import androidx.room.Entity;

@Entity(primaryKeys = {"setId", "cardId"})
public class SetCardCrossRef {
    public long setId;
    public long cardId;
}
