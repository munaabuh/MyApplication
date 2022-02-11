package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyTherapistsArrayListActivity extends AppCompatActivity {

    //the object of the view - design
    private ListView myListView;
    //object containing the items to be displayed - Data
    private ArrayList<Therapist> list;
    //the object for the adapter connecting the data to the view
    private MyTherapistsAdapter myAdapter;
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    private String UID = maFirebaseAuth.getUid();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");
    private  DatabaseReference myRef = database.getReference("users/" +UID +"/favorites");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_therapists_array_list);
        Toast.makeText(this, "UID:" + UID, Toast.LENGTH_LONG).show();


        list = new ArrayList<>();

        //reference to the list view so it can be programmed
        myListView = findViewById(R.id.myListView);

        //connect adapter with data
        myAdapter = new MyTherapistsAdapter(this, R.layout.item_row, list);

        //connect adapter with view
        myListView.setAdapter(myAdapter);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Therapist therapist = snapshot.getValue(Therapist.class);
                list.add(therapist);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                    Therapist myTherapists = dataSnapshot.getValue((Therapist.class));
                    list.add(myTherapists);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void add(View view){
        Intent i = new Intent(this, PsychiatristListActivity.class);
        startActivity(i);

    }

    public void backwards(View view){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

}