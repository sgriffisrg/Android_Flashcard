package com.example.flashcards;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FlashcardDAO {
    @Insert
    void addNewCard(Flashcard card);

    @Insert
    void addNewSet(Sets set);

    @Delete()
    void deleteCard(Flashcard cardToDelete);

    @Update
    void updatedSet(Sets setToBeUpdated);

    @Query("Delete From Flashcard")
    void byebye();

    @Delete()
    void deleteSet(Sets setToDelete);

    @Query("Delete From Flashcard where cardId in (:idList)")
    void deleteCardsById(List<Long> idList);

    @Query("Delete From Flashcard Where setOwnedId == :setId")
    void deleteCardsInSet(long setId);

    @Query("Select * From Flashcard Where setOwnedId == :setId")
    List<Flashcard> getCardsInSet(long setId);

    @Query("Select * From Sets Where name == :setName")
    Sets getOneSet(String setName);

    @Query("Select * From Sets Where setId == :setId")
    Sets getOneSetByID(long setId);

    @Query("Select * FROM Flashcard where clone == 0")
    List<Flashcard> getAllCards();

    @Query("Select * FROM Sets")
    List<Sets> getAllSets();

    @Query("Select * FROM Categories")
    List<Categories> getAllCategories();

    @Transaction
    @Query("SELECT * FROM Sets")
    List<SetWithCards> getSetsWithCards();

    @Query("Select * from Flashcard where term == :term and description == :definition")
    List<Flashcard> getAllClones(String term, String definition);

}
