package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutUsDetailActivity extends AppCompatActivity {
    private TextView tvFileContent;
    private FileHandler fileHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_detail);

        tvFileContent= findViewById(R.id.tvFileContent);

        String category= getIntent().getStringExtra("category");
        loadContentByCategory(category);

        if(fileHandler!=null)
            tvFileContent.setText(fileHandler.getContent());
    }

    public void loadContentByCategory(String category){
        if(category.equals("missionAndValues")){
            fileHandler= new FileHandler("OurMissionAndValues_txt",this);
            fileHandler.readFile();
        }
       else if(category.equals("medicalExpertise")){}
       else if(category.equals("privacyAndSecurity")){}
       else {}
    }
    public void back(View view){
        Intent intent= new Intent(AboutUsDetailActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }
}