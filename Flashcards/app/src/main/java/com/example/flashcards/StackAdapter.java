package com.example.flashcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StackAdapter extends BaseAdapter {
    private List<Flashcard> cards;
    private Context context;

    public StackAdapter(List<Flashcard> list, Context context){
        cards = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int i) {
        return cards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        TextView text;
        if(v == null){
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
            text = v.findViewById(R.id.card_text);
            text.setText(cards.get(i).term);
        }
        else{
            text = v.findViewById(R.id.card_text);
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().toString() == cards.get(i).term)
                    text.setText(cards.get(i).description);
                else
                    text.setText(cards.get(i).term);


            }
        });

        return v;
    }
}
