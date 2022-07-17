package com.example.flashcards;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetDialogFragment extends DialogFragment {

    public interface DialogClickListener {
        void dialogListenerCreateSet(String setName);
        void dialogListenerCancel();
    }

    public DialogClickListener listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String Tag = "tag";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SetDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetDialogFragment newInstance() {
        SetDialogFragment fragment = new SetDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.SetStyle);
    }

/*    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("My Title");
        return dialog;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setCancelable(false);
        getDialog().setTitle("Create Set");
        View v = inflater.inflate(R.layout.fragment_set_dialog, container, false);
        EditText setName = v.findViewById(R.id.SetName);
        Button cancel = v.findViewById(R.id.cancelSet);
        Button createSet = v.findViewById(R.id.createSet);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.dialogListenerCancel();
                dismiss();
            }
        });
        createSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setName.getText().toString().isEmpty()){
                    Toast.makeText(view.getContext(), "Please enter a name for the set", Toast.LENGTH_SHORT);
                }
                else {
                    listener.dialogListenerCreateSet(setName.getText().toString());
                    dismiss();
                }
            }
        });
        return v;
    }
}