package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.nio.channels.InterruptedByTimeoutException;

public class ProfileActivity extends AppCompatActivity {

    //request for camera activity result
   // private static final int CAMERA_REQUEST = 0;
    //private static final int GALLERY_REQUEST = 1;

    //attributes
    private ImageView imageViewProfile;
    //private Button buttonCamera, reminderButton, therapistButton, appointmentButton;
    private TextView userTV, name;

    //for picture of camera
    private Bitmap picture;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //gets reference for the design components
        //buttonCamera = findViewById(R.id.buttonCamera);
        //buttonCamera.setOnClickListener(this);
        //buttonCamera.setOnLongClickListener(this);



       imageViewProfile = findViewById(R.id.imageViewProfile);

        userTV = findViewById(R.id.userTV);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();

        userTV.setText(email);

        name= findViewById(R.id.name);
        mAuth = FirebaseAuth.getInstance();
        String name= mAuth.getCurrentUser().getDisplayName();
        String n= mAuth.getCurrentUser().getEmail();
        Log.i("Stam","name"+name+" n"+n);


    }





    @Override
    //inflates the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.aboutUs_menu:
                Intent i= new Intent(this, AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.exit_menu:
                this.finish();
                break;
            case R.id.help_menu:
                Intent intent= new Intent(this, HelpActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



    public void reminders(View view){
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);
    }

    public void appointments(View view){
        Intent intent = new Intent(this, AppointmentListActivity.class);
        startActivity(intent);
    }

    public void myTherapists(View view){
        Intent intent = new Intent(this, MyTherapistsArrayListActivity.class);
        startActivity(intent);
    }

}