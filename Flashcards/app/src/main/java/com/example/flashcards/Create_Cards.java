package com.example.flashcards;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Create_Cards#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Create_Cards extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String Tag = "tag";

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
    public static Create_Cards newInstance(String param1) {
        Create_Cards fragment = new Create_Cards();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
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
        View v = inflater.inflate(R.layout.fragment_set_dialog, container, false);
        return v;

    }
}