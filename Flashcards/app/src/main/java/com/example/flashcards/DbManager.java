package com.example.flashcards;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbManager {

    interface DbListener{
        void added(String setName);
        void addedCard(String cardTerm);
        void setListReady(List<Sets> setList);
        void flashcardListReady(List<Flashcard> cardList);
        void deletedSet(String setName);
        void cloneCheck(int totalAmt, String term, String definition, long setId, String setName);
        void setUpdated(String setName);
        void setReady(Sets set);
        void deleteSet(Sets set);
        void bye();
        void getSet(Sets set);
        void cloneCardsReady(List<Flashcard> cards);
        void deleteCard(String term);
    }
    DbListener listener;
    static FlashcardDB db = null;

    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler mainThread_Handler = new Handler(Looper.getMainLooper());

    private static void buildInstanceDb(Context context){ db = Room.databaseBuilder(context, FlashcardDB.class, "flashcard_db").fallbackToDestructiveMigration().build(); }

    public FlashcardDB getDatabase(Context context){
        if(db == null){
            buildInstanceDb(context);
        }
        return db;
    }

    public void addNewSet(Sets set){
        databaseExecutor.execute(() -> {
            db.cardsDao().addNewSet(set);

            mainThread_Handler.post(() -> listener.added(set.name));
        });
    }

    public void addNewCard(Flashcard card){
        databaseExecutor.execute(() -> {
            db.cardsDao().addNewCard(card);
            mainThread_Handler.post(() -> listener.addedCard(card.term));
        });
    }

    public void getAllSets(){
        databaseExecutor.execute(() -> {
            List<Sets> sets = db.cardsDao().getAllSets();
            mainThread_Handler.post(() -> listener.setListReady(sets));
        });
    }

    public void deleteSet(Sets set){
        databaseExecutor.execute(() -> {
            db.cardsDao().deleteSet(set);
            mainThread_Handler.post(() -> listener.deletedSet(set.name));
        });
    }

    public void getAllCards(){
        databaseExecutor.execute(() -> {
            List<Flashcard> flashcards = db.cardsDao().getAllCards();
            mainThread_Handler.post(() -> listener.flashcardListReady(flashcards));
        });
    }

    public void checkForClones(String term, String definition, long setId, String setName){
        databaseExecutor.execute(() -> {
            List<Flashcard> cards = db.cardsDao().getAllClones(term, definition);
            mainThread_Handler.post(() -> listener.cloneCheck(cards.size(), term, definition, setId, setName));
        });
    }

    public void deleteCardsInSet(long setId, Sets set){
        databaseExecutor.execute(() -> {
            db.cardsDao().deleteCardsInSet(setId);
            mainThread_Handler.post(() -> listener.deleteSet(set));
        });
    }

    public void byebye(){
        databaseExecutor.execute(() -> {
            db.cardsDao().byebye();
            mainThread_Handler.post(() -> listener.bye());
        });
    }
    public void getOneSet(String setName){
        databaseExecutor.execute(() -> {
            Sets set = db.cardsDao().getOneSet(setName);
            mainThread_Handler.post(() -> listener.getSet(set));
        });
    }

    public void updateSet(Sets set){
        databaseExecutor.execute(() -> {
            db.cardsDao().updatedSet(set);
            mainThread_Handler.post(() -> listener.setUpdated(set.name));
         });
    }

    public void getCardsInSet(long setId){
        databaseExecutor.execute(() -> {
            List<Flashcard> cards = db.cardsDao().getCardsInSet(setId);
            mainThread_Handler.post(() -> listener.flashcardListReady(cards));
        });
    }

    public Sets getSetByID(long setId){
        return db.cardsDao().getOneSetByID(setId);
    }

    public void getAllClones(String term, String definition){
        databaseExecutor.execute(() -> {
            List<Flashcard> cards = db.cardsDao().getAllClones(term, definition);
            mainThread_Handler.post(() -> listener.cloneCardsReady(cards));
        });
    }

    public void deleteCard(Flashcard card){
        databaseExecutor.execute(() -> {
            db.cardsDao().deleteCard(card);
            mainThread_Handler.post(() -> {
                listener.deleteCard(card.term);
            });
        });
    }

    public void deleteCards(List<Long> idList){
        databaseExecutor.execute(() -> {
            db.cardsDao().deleteCardsById(idList);
            mainThread_Handler.post(() ->{
               listener.deleteCard("Cards");
            });
        });
    }
}
