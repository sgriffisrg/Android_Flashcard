package com.example.flashcards;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFrontFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFrontFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CardFrontFragment() {
        // Required empty public constructor
    }
    interface CardFrontInterface{
        void clicked();
    }
    public CardFrontInterface listener;
    private SwipeDeck cardStack;
    private static List<Flashcard> cards;
    StackAdapter adapter;
    public boolean showingback = false;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CardFrontFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardFrontFragment newInstance(List<Flashcard> cardList) {
        CardFrontFragment fragment = new CardFrontFragment();
        Bundle args = new Bundle();
        cards = cardList;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.card_fragment_front, container, false);
        adapter = new StackAdapter(cards, getContext());
        cardStack = (SwipeDeck)v.findViewById(R.id.swipe_deck);
        cardStack.setAdapter(adapter);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {
            }

            @Override
            public void cardsDepleted() {
                listener.clicked();
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });
        return v;
    }

}