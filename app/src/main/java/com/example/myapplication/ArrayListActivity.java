package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArrayListActivity extends AppCompatActivity{

    //the object of the view - design
    private ListView myListView;
    //object containing the items to be displayed - Data
    private ArrayList<Item> list;
    //the object for the adapter connecting the data to the view
    private CustomAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        list =  new ArrayList<>();
        list.add(new Item(50, true, "This is my first Item", R.drawable.pretty));
        list.add(new Item(50, true, "This is my second Item", R.drawable.pretty));
        list.add(new Item(50, true, "This is my third Item", R.drawable.pretty));
        list.add(new Item(50, true, "This is my forth Item", R.drawable.pretty));
        list.add(new Item(50, true, "This is my fifth Item", R.drawable.pretty));

        //reference to the list view so it can be programmed
        myListView = findViewById(R.id.mylistview);

        //connect adapter with data
        myAdapter = new CustomAdapter(this, R.layout.item_row, list);

        //connect adapter with view
        myListView.setAdapter(myAdapter);
        //connects click listener to items in the list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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

    }


}