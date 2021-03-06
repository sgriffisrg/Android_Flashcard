package com.example.flashcards;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class SetWithCards {
    @Embedded public Sets set;
    @Relation(
            parentColumn = "setId",
            entityColumn = "setOwnedId"
    )
    public List<Flashcard> cards;
}
