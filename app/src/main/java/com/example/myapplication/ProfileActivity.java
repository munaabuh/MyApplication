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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.nio.channels.InterruptedByTimeoutException;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    //request for camera activity result
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    //attributes
    private Button buttonCamera;
    private ImageView imageViewProfile;

    //for picture of camera
    private Bitmap picture;

    private FirebaseAuth maFirebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //gets reference for the design components
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);
        buttonCamera.setOnLongClickListener(this);

        imageViewProfile = findViewById(R.id.imageViewProfile);

        //write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");
        String UID = maFirebaseAuth.getUid();
        DatabaseReference myRef = database.getReference("users/"+UID);

        //todo change this into the object you need to use (reminder / appointemnt)
        myRef.push().setValue(new Item(2,true,"this is my first item",7));
    }

    @Override
    public void onClick(View view) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST){
            if(resultCode == RESULT_OK){
                picture = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                imageViewProfile.setImageBitmap(picture);
            }
        }
        else{
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                try{
                    //decode an input stream into a bitmap
                    picture = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageViewProfile.setImageBitmap(picture);
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
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
            case R.id.settings_menu:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_menu:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View view) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, GALLERY_REQUEST);

        return false;
    }
}