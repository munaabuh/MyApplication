package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PsychiatristAdapter extends ArrayAdapter<Therapist>{

    private int resource;
    private Context context;
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    private String UID = maFirebaseAuth.getUid();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");
    private  DatabaseReference myRef = database.getReference("users/" +UID +"/favorites");



    public PsychiatristAdapter(@NonNull Context context, int resource, @NonNull List<Therapist> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);

        //method from the android studio, not related to Item object (the class we created).
        Therapist Psychiatrist = getItem(position);

        if(Psychiatrist != null) {
            TextView textViewDescription = view.findViewById(R.id.textViewDesc);
            textViewDescription.setText(Psychiatrist.toString());


            ImageButton favorite = view.findViewById(R.id.favorite);
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myRef.push().setValue(Psychiatrist);
                    favorite.setImageResource(R.drawable.filled_person_icon);
                }
            });

        }
        return view;
    }
}
