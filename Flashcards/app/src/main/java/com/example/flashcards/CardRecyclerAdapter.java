package com.example.flashcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder> {
    List<Flashcard> cardList;
    Context context;

    interface CardListener{
        void cardClicked(Flashcard card);
        void longCardClicked(Flashcard card);
    }

    CardListener listener;

    public CardRecyclerAdapter(List<Flashcard> cards, Context con){
        context = con;
        cardList = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_list_layout, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String cloneAmt = "Clones: " + cardList.get(position).clone;
        holder.cardTerm.setText(cardList.get(position).term);
        holder.clones.setText(cloneAmt);
    }

    @Override
    public int getItemCount() { return cardList.size(); }

    class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView clones;
        TextView cardTerm;
        public CardViewHolder(@NotNull View itemView){
            super(itemView);
            clones = itemView.findViewById(R.id.cloneAmt);
            cardTerm = itemView.findViewById(R.id.cardTerm);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.cardClicked(cardList.get(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View view) {
            listener.longCardClicked(cardList.get(getAdapterPosition()));
            return true;
        }
    }
}
