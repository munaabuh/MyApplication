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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.channels.InterruptedByTimeoutException;

public class ProfileActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener, View.OnLongClickListener{

    //request for camera activity result
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    //attributes
    private Button buttonCamera;
    private ImageView imageViewProfile;
    private TextView userTV, name, surName,email,birthday, password;

    //for picture of camera
    private String pic;
    private Bitmap picture;

    private User u;
    private DatabaseReference userRef;
    //get instance of authentication project in FB console
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    //gets the root of the real time database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");


    private FirebaseUser user= mAuth.getCurrentUser();
    private DatabaseReference myRef= database.getReference("/users"+ user.getUid());

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
        String Email = mAuth.getCurrentUser().getEmail();
        userTV.setText(Email);

        name= findViewById(R.id.name);
        surName= findViewById(R.id.surName);
        email= findViewById(R.id.email);
        birthday= findViewById(R.id.birthday);
        password= findViewById(R.id.password);

        user = mAuth.getCurrentUser();
        myRef= database.getReference("users/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if(user.getUid().equals(dataSnapshot.getKey())){
                        User u= dataSnapshot.getValue(User.class);
                        updateUserData(u);
                    }
                }
            }

            private void updateUserData(User user){
                name.setText((user.getName()));
                surName.setText((user.getSurName()));
                email.setText((user.getEmail()));
                birthday.setText((user.getBirthday()));
                password.setText((user.getPassword()));
                if(user.getImage() != null)
                {
                    Bitmap b= stringToBitMap(user.getImage());
                    imageViewProfile.setImageBitmap(b);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveImage(Bitmap bitmap){
        DatabaseReference myRef1= database.getReference("users/" + user.getUid());
        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] arr= baos.toByteArray();
        pic= Base64.encodeToString(arr, Base64.DEFAULT);
        myRef1.child("image").setValue(pic);

    }

    public Bitmap stringToBitMap(String image){
        try{
            byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);

            InputStream inputStream  = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
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
                saveImage(picture);
            }
        }
        else{
            if(resultCode == RESULT_OK){
                Uri targetUri = data.getData();
                try{
                    //decode an input stream into a bitmap
                    picture = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageViewProfile.setImageBitmap(picture);
                    saveImage(picture);
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
                this.closeApplication();
                break;
            case R.id.help_menu:
                Intent intent= new Intent(this, HelpActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void closeApplication(){
        this.finish();
        moveTaskToBack(true);
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
