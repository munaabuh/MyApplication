package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class DetailActivity extends AppCompatActivity {
    private TextView tvFileContent;
    private FileHandler fileHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvFileContent = findViewById(R.id.tvFileContent);

        String category = getIntent().getStringExtra("category");
        loadContentByCategory(category);

        if(fileHandler!=null)
            tvFileContent.setText(fileHandler.getContent());



    }
    public void loadContentByCategory(String category){
        if(category.equals("mentalHealth")){
            fileHandler = new FileHandler("MentalHealth_txt", this);
            fileHandler.readFile();

        }
        else if(category.equals("mindfulness")){}
        else if(category.equals("therapy and treatment")){}
        else if(category.equals("anxiety")){}
        else if(category.equals("anxietyTypes")){}
        else if(category.equals("anxietyCauses")){}
        else if(category.equals("anxietyTreatment")){}
        else if(category.equals("emotionalEating")){}
        else if(category.equals("emotionalEatingSigns")){}
        else if(category.equals("emotionalEatingCauses")){}
        else if(category.equals("emotionalEatingTreatment")){}
        else if(category.equals("stress")){}
        else if(category.equals("stressTypes")){}
        else if (category.equals("stressCauses")){}
        else if (category.equals("stressTreatment")){}
        else if(category.equals("depression")){}
        else if(category.equals("depressionTypes")){}
        else if(category.equals("depressionCauses")){}
        else if(category.equals("depressionTreatment")){}
    }
}