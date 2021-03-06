package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArrayListActivity extends AppCompatActivity{

    //the object of the view - design
    private ListView myListView;
    //object containing the items to be displayed - Data
    private ArrayList<Reminder> list;
    //the object for the adapter connecting the data to the view
    private CustomAdapter myAdapter;
    //get instance of authentication project in FB console
    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();
    //gets the root of the real time database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        String UID = maFirebaseAuth.getUid();
        Toast.makeText(this, "UID:"+UID, Toast.LENGTH_LONG).show();
        //build reference fo user related data in real time database suing user ID
        DatabaseReference myRef = database.getReference("users/"+UID+"/Reminders");

        //adds an item to the FB under the reference specified
        //TODO change this into the object you need to use (reminder / appointment)
        //TODO alter the code so the data is uploaded to the firebase only when the user clicks the button
        //myRef.push().setValue(new Item(2,true,"this is my first item",R.id.imageItem));

       list =  new ArrayList<>();

        //reference to the list view so it can be programmed
        myListView = findViewById(R.id.myListView);

        //connect adapter with data
        myAdapter = new CustomAdapter(this, R.layout.item_row, list);

        //connect adapter with view
        myListView.setAdapter(myAdapter);
        //connects click listener to items in the list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //myRef.push().setValue(new Item(2,true,"this is my first item",R.id.imageItem));
              Toast.makeText(getApplicationContext(),"Reminder:" + list.get(i), Toast.LENGTH_LONG).show();
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
                    Reminder reminder1 = dataSnapshot.getValue((Reminder.class));
                    list.add(reminder1);
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void add(View view){
        Intent i = new Intent(this, AddReminderActivity.class);
        startActivity(i);

    }

    public void back(View view){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

}