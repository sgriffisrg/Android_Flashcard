package com.example.flashcards;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface FlashcardDAO {
    @Insert
    void addNewCard(Flashcard card);

    @Insert
    void addNewSet(Sets set);

    @Delete()
    void deleteCard(Flashcard cardToDelete);

    @Delete()
    void deleteSet(Sets setToDelete);

    @Query("Select * FROM Flashcard")
    List<Flashcard> getAllCards();

    @Query("Select * FROM Sets")
    List<Sets> getAllSets();

    @Query("Select * FROM Categories")
    List<Categories> getAllCategories();

    @Transaction
    @Query("SELECT * FROM Sets")
    public List<SetWithCards> getSetsWithCards();

}
