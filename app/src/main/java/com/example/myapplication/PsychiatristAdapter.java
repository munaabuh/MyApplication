package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PsychiatristAdapter extends ArrayAdapter<Therapist> {

    private int resource;
    private Context context;

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

        }
        return view;
    }
}
