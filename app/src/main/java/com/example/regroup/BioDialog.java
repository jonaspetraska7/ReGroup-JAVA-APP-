package com.example.regroup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BioDialog extends DialogFragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid;
    Button accept;
    Button dismiss;
    private EditText bioET;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bio_dialog, container, false);

        uid = getArguments().getString("uid");

        accept = view.findViewById(R.id.acceptBio);
        dismiss = view.findViewById(R.id.dismissBio);
        bioET = view.findViewById(R.id.bioEditText);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBio(bioET.getText().toString());
                dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    public void setBio(String bio) {

        DocumentReference docRef = db.collection("users").document(uid);
        docRef.update("Bio", bio);
    }
}
