package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        String name = getIntent().getStringExtra("name");
        // welcomeNote.setText("Hi there!");

    }
    public void logIn(View view){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", this);
        builder.setNegativeButton("No", this);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == dialogInterface.BUTTON_POSITIVE){
            super.onBackPressed();
            dialogInterface.cancel();
        }
        if(i==dialogInterface.BUTTON_NEGATIVE){
            dialogInterface.cancel();
        }
    }

    @Override
    //inflates the design of the required menu on top of the activity
    public boolean onCreateOptionsMenu(Menu menu){
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
                this.closeApplication();// closeApplication();
                break;
            case R.id.help_menu:
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void closeApplication(){
        this.finish();
        moveTaskToBack(true);
    }

}






