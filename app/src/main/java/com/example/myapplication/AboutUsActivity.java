package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private ImageView backArrow;
    private CardView missionAndValues,medicalExpertise,privacyAndSecurity,creators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        missionAndValues= findViewById(R.id.missionAndValues);
        missionAndValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
                intent.putExtra("category","missionAndValues");
                startActivity(intent);
            }
        });

        medicalExpertise= findViewById(R.id.medicalExpertise);
        medicalExpertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
                intent.putExtra("category", "medicalExpertise");
                startActivity(intent);
            }
        });

        privacyAndSecurity= findViewById(R.id.privacyAndSecurity);
        privacyAndSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
                intent.putExtra("category", "privacyAndSecurity");
                startActivity(intent);
            }
        });

        creators= findViewById(R.id.creators);
        creators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
                intent.putExtra("category", "creators");
                startActivity(intent);
            }
        });

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutUsActivity.super.onBackPressed();
            }
        });
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


}