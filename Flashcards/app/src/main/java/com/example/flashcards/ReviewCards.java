package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ReviewCards extends AppCompatActivity implements View.OnClickListener, CardFrontFragment.CardFrontInterface {
    ArrayList<Flashcard> cards = new ArrayList<Flashcard>();
    CardFrontFragment front;
    FrameLayout cardLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cards);
        cards = (ArrayList<Flashcard>) ((AppManager)getApplication()).cardsToBeReviewed;
        front = new CardFrontFragment().newInstance(cards);
        front.listener = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.card_view, front)
                    .commit();
        }
        cardLayout = findViewById(R.id.card_view);
        cardLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void clicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Return")
                .setMessage("Do you want to return to the homepage or the previous page?")
                .setPositiveButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Previous", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), SetDetails.class);
                        intent.putExtra("setId", cards.get(0).setOwnedId);
                        startActivity(intent);
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}