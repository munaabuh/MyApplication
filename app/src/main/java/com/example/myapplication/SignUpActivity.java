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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    private Button buttonSubmit;
    private RadioButton radioButtonFemale, radioButtonMale;
    private EditText editTextName, editTextSurname,editTextEmail, editTextBirthday, editTextPassword;
    private TextView textViewGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextBirthday = findViewById(R.id.editTextBirthday);
        editTextPassword = findViewById(R.id.editTextPassword);


        textViewGender = findViewById(R.id.textViewGender);

    }

    public void submit(View view){

        Intent intent = new Intent(this, MainActivity.class);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if(!email.equals("") && email.contains("@") && validate(email)){
            intent.putExtra("name", email);
            if(!password.equals("") && validate(password))
                startActivity(intent);
        }

    }

    public boolean validate(String str){

        Pattern upperCase =  Pattern.compile("[A - Z]");
        Pattern lowerCase = Pattern.compile("[a - z]");
        Pattern number = Pattern.compile("[0 - 9]");

        if(!upperCase.matcher(str).find())
            return false;

        if(!lowerCase.matcher(str).find())
            return false;

        if(!number.matcher(str).find())
            return false;

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