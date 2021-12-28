package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String category = getIntent().getStringExtra("category");
        loadContentByCategory(category);

    }
    public void loadContentByCategory(String category){
        if(category.equals("mentalHealth")){}
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