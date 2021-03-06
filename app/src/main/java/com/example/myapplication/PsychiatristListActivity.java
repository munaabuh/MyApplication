package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PsychiatristListActivity extends AppCompatActivity {

    private ImageView backArrow;

    //the object of the view - design
    private ListView myListView;
    //object containing the items to be displayed - Data
    private ArrayList<Therapist> list;
    //the object for the adapter connecting the data to the view
    private PsychiatristAdapter myAdapter;
    //get instance of authentication project in FB console
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychiatrist_list);
        String UID = maFirebaseAuth.getUid();
        Toast.makeText(this, "UID:" + UID, Toast.LENGTH_LONG).show();
        //build reference fo user related data in real time database suing user ID
        DatabaseReference myRef = database.getReference("therapists/" + UID + "/therapists");

        list = new ArrayList<>();
        list.add(new Therapist("0723971429", "Elisabeth Wajnryt","Jerusalem","Eating Disorders Specialist","female" ));
        list.add(new Therapist("0723971357", "Orli Jacobs", "Ra'nana", "Mental Health Counselor", "female"));
        list.add(new Therapist("0723971263", "Wendy Gordon", "Tel Aviv", "Clinical Social Worker","female"));
        list.add(new Therapist("0723971414", "Liat Gamzo", "Tel Aviv", "Clinical Psychologist", "female"));
        list.add(new Therapist("0723971387", "Natasha Miller Gutman", "Tel Aviv , Hirzelya", "Expressive Arts Therapist", "female"));
        list.add(new Therapist("0723971435", "Jennifer Turgeman", "Herzliya, Pituach, Tel Aviv", "Creative Arts Therapist", "female"));

        //database.getReference("therapists/" + UID + "/therapists").push().setValue(new Therapist("0723971435", "Jennifer Turgeman", "Herzliya,
        // Pituach, Tel Aviv", "Creative Arts Therapist", "female"));

        //reference to the list view so it can be programmed
        myListView = findViewById(R.id.myListView);

        //connect adapter with data
        myAdapter = new PsychiatristAdapter(this, R.layout.therapists_row, list);

        //connect adapter with view
        myListView.setAdapter(myAdapter);
        //connects click listener to items in the list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //myRef.push().setValue(new Item(2,true,"this is my first item",R.id.imageItem));
                Toast.makeText(getApplicationContext(),"Item:" + list.get(i), Toast.LENGTH_LONG).show();
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Therapist therapist1 = dataSnapshot.getValue((Therapist.class));
                    list.add(therapist1);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PsychiatristListActivity.super.onBackPressed();
            }
        });
    }


}