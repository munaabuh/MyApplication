package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private CardView missionAndValues,medicalExpertise,privacyAndSecurity,membership;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        missionAndValues= findViewById(R.id.missionAndValues);
        medicalExpertise= findViewById(R.id.medicalExpertise);
        privacyAndSecurity= findViewById(R.id.privacyAndSecurity);
        membership= findViewById(R.id.membership);


    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
}