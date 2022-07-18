package com.example.flashcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SetRecyclerAdapter extends RecyclerView.Adapter<SetRecyclerAdapter.SetViewHolder> {
    ArrayList<Sets> setList;
    Context context;

    interface SetListener{
        public void setClicked();
    }

    SetListener listener;

    public SetRecyclerAdapter(ArrayList<Sets> sets, Context con){
        setList = sets;
        context = con;
    }

    @NonNull
    @Override
    public SetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.set_row_layout, parent, false);
        return new SetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SetViewHolder holder, int position) {
        String cardNum = "Flashcards: " + setList.get(position).cardAmt;
        holder.setName.setText(setList.get(position).name);
        holder.cardAmt.setText(cardNum);
    }

    @Override
    public int getItemCount() { return setList.size(); }


    class SetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView setName;
        TextView cardAmt;
        public SetViewHolder(@NotNull View itemView){
            super(itemView);
            setName = itemView.findViewById(R.id.displaySetName);
            cardAmt = itemView.findViewById(R.id.displayCardAmount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.setClicked();
        }
    }
}
