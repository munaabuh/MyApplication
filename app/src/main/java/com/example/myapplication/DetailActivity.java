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
        else if(category.equals("anxiety")){}
        else if(category.equals("anxietyCauses")){}
        else if(category.equals("anxietyTreatment")){}
        else if(category.equals("anxietyImpact")){}
        else if(category.equals("overthinking")){}
        else if(category.equals("overthinkingCauses")){}
        else if(category.equals("overthinkingTreatment")){}
        else if(category.equals("overthinkingImpact")){}
        else if(category.equals("stress")){}
        else if (category.equals("stressCauses")){}
        else if (category.equals("stressTreatment")){}
        else if(category.equals("stressImpact")){}
        else if(category.equals("depression")){}
        else if(category.equals("depressionCauses")){}
        else if(category.equals("depressionDiagnosis")){}
        else if(category.equals("depressionTreatment")){}

    }
}