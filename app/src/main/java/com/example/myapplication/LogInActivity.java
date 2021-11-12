package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity implements View.OnLongClickListener, DialogInterface.OnClickListener {

    private FirebaseAuth mAuth;
    private Button buttonLogIn, buttonSignUp;
    private static final String TAG = "FIREBASE";
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //returns a reference to the object of the project Firebase
        mAuth = FirebaseAuth.getInstance();

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

        //Intent intent = new Intent(this, MainActivity.class);
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
            //intent.putExtra("name", editTextEmail.getText().toString());

            login(editTextEmail.getText().toString(), editTextPassword.getText().toString());

            //startActivity(intent);

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
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
   public void login(String email, String password){
       mAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Log.d(TAG, "signInWithEmail:success");
                           FirebaseUser user = mAuth.getCurrentUser();
                           Intent i = new Intent(LogInActivity.this, MainActivity.class);
                           startActivity(i);

                       } else {
                           // If sign in fails, display a message to the user.
                           Log.w(TAG, "signInWithEmail:failure", task.getException());
                           Toast.makeText(LogInActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                       }

                       // ...
                   }
               });
   }
}