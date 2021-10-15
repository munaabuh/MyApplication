package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, DialogInterface.OnClickListener {

    private Button buttonLogIn, buttonSignUp;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // sets the required button to respond to long click, otherwise it won't.
        buttonLogIn.setOnLongClickListener(this);

        SharedPreferences sp = getSharedPreferences("settings",MODE_PRIVATE);
        String email = sp.getString("email", "");
        String password = sp.getString("password", "");

        if(!email.equals("") && !password.equals("") ){
            editTextEmail.setText(email);
            editTextPassword.setText(password);

        }
    }

    public void logIn(View view){

        Intent intent = new Intent(this, WelcomeActivity.class);
        if(!editTextEmail.getText().toString().equals("") && editTextEmail.getText().toString().contains("@") && !editTextPassword.getText().toString().equals("")){

            //saving email & password of the user in a local file for future use
            // creating a shared preference (sp)
            SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);

            //opening the editor for editing
            SharedPreferences.Editor editor = sp.edit();

            //writing the wanted settings
            editor.putString("email", editTextEmail.getText().toString());
            editor.putString("password", editTextPassword.getText().toString());

            //saving & closing the file
            editor.commit();

           // intent.putExtra("name", editTextEmail.getText().toString());
            startActivity(intent);

        }

    }

    public void signUp(View view){

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view){

        editTextEmail.setText("");
        editTextPassword.setText("");
        return true;

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
                // closeApplication();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}