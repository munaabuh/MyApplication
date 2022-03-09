package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements DialogInterface.OnClickListener{


    private FirebaseAuth mAuth;
    private Button buttonSubmit;
    private TextView textViewGender;
    private static final String TAG = "FIREBASE";
    private RadioButton radioButtonFemale, radioButtonMale;
    private EditText editTextName, editTextSurname,editTextEmail, editTextBirthday, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

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

        //Intent intent = new Intent(this, MainActivity.class);

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        signUp(editTextEmail.getText().toString(), editTextPassword.getText().toString());

        //if(!email.equals("") && email.contains("@") && validate(email)){
           // intent.putExtra("name", email);
            //if(!password.equals("") && validate(password))
                //startActivity(intent);

        }



   /* public boolean validate(String str){

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

    }*/

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

    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                            User u=new User(editTextName.getText()+"",editTextSurname.getText()+"",editTextEmail.getText()+"",(Date)editTextBirthday.getText());
                            FirebaseDatabase db=FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");
                            DatabaseReference root=db.getReference("users/"+mAuth.getCurrentUser().getUid());
                            root.push().setValue(u);
                            Log.i("Stam", u.toString());
                            Log.i("Stam",mAuth.getCurrentUser().getUid());


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}