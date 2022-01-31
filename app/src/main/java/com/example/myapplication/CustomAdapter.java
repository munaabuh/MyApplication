package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Reminder>{

    private int resource;
    private Context context;


    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Reminder> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource; // this is the item row resource, the design for each row
    }

    /*

     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(context).inflate(resource, parent, false);
        Reminder item = getItem(position); //method from the android studio, not related to Item object (the class we created).
        if(item != null){

            TextView textViewDescription = view.findViewById(R.id.textViewDesc);


            //imageView.setImageResource(item.getResid());
            textViewDescription.setText(item.getTime() + item.getDate());


        }
        return view;
    }
}
