package com.example.flashcards;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Create_Cards#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Create_Cards extends DialogFragment {

    public interface CardDialogClickListener {
        void dialogListenerAddCard(String term, String description, long setId, String setName);
        void cardDialogListenerCancel(Sets set);
    }
    CardDialogClickListener listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String Tag = "tag";
    public static Sets sets;

    // TODO: Rename and change types of parameters
    private String setName;

    public Create_Cards() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Create_Cards.
     */
    // TODO: Rename and change types and number of parameters
    public static Create_Cards newInstance(String param1, Sets set) {
        Create_Cards fragment = new Create_Cards();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        sets = set;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setName = getArguments().getString(ARG_PARAM1);
        }
        setStyle(DialogFragment.STYLE_NORMAL,R.style.SetStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setCancelable(false);
        getDialog().setTitle("Add Cards");
        View v = inflater.inflate(R.layout.fragment_create__cards, container, false);
        Button cancel = v.findViewById(R.id.cancelCard);
        Button add = v.findViewById(R.id.createCard);
        TextInputEditText term = v.findViewById(R.id.makeTerm);
        TextInputEditText definition = v.findViewById(R.id.makeDefinition);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "All Done", Toast.LENGTH_SHORT).show();
                listener.cardDialogListenerCancel(sets);
                dismiss();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(term.getText().toString().isEmpty() || definition.getText().toString().isEmpty()){
                    Toast.makeText(view.getContext(), "Please make sure you've filled out each box", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(), "A card for " + term.getText().toString() + " has been made.", Toast.LENGTH_SHORT).show();
                    listener.dialogListenerAddCard(term.getText().toString(), definition.getText().toString(), sets.setId, sets.name);
                    term.setText("");
                    definition.setText("");
                    term.requestFocus();
                }
            }
        });
        return v;

    }
}