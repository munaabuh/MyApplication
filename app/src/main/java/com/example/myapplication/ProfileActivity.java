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
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.channels.InterruptedByTimeoutException;

public class ProfileActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener, View.OnLongClickListener{

    //request for camera activity result
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    //attributes
    private ImageView imageViewProfile;
    private Button buttonCamera, reminderButton, therapistButton, appointmentButton;
    private TextView userTV, name;

    //for picture of camera
    private String pic;
    private Bitmap picture;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //gets reference for the design components
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);
        buttonCamera.setOnLongClickListener(this);

       imageViewProfile = findViewById(R.id.imageViewProfile);

        userTV = findViewById(R.id.userTV);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();

        userTV.setText(email);

    }

    public void bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        this.pic= Base64.encodeToString(arr, Base64.DEFAULT);
    }

    @Override
    public boolean onLongClick(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, GALLERY_REQUEST);

        return false;
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

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
    }
}
