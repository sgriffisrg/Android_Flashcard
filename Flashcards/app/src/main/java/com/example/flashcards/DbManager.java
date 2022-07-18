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
        void deleted(String name);
        void setUpdated(String setName);
        void setReady(Sets set);
    }
    DbListener listener;
    static FlashcardDB db = null;

    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler mainThread_Handler = new Handler(Looper.getMainLooper());

    private static void buildInstanceDb(Context context){ db = Room.databaseBuilder(context, FlashcardDB.class, "flashcard_db").fallbackToDestructiveMigration().build(); }

    public FlashcardDB getDatabase(Context context){
        if(db ==null){
            buildInstanceDb(context);
        }
        return db;
    }

    public void addNewSet(Sets set){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.cardsDao().addNewSet(set);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() { listener.added(set.name);}
                });
            }
        });
    }

    public void addNewCard(Flashcard card){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.cardsDao().addNewCard(card);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() { listener.added(card.term);}
                });
            }
        });
    }

    public void getAllSets(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Sets> sets = db.cardsDao().getAllSets();
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() { listener.setListReady(sets);}
                });
            }
        });
    }
}
