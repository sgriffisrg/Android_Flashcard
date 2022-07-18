package com.example.flashcards;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Flashcard.class, Sets.class, Categories.class, SetCardCrossRef.class}, version = 6)
    public abstract class FlashcardDB extends RoomDatabase {
        public abstract FlashcardDAO cardsDao();
}

