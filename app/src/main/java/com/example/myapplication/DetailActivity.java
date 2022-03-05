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
        else if(category.equals("mindfulness")){
            fileHandler = new FileHandler("Mindfulness_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("therapy and treatment")){
            fileHandler = new FileHandler("Therapy_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("anxiety")){
            fileHandler = new FileHandler("Anxiety_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("anxietyTypes")){
            fileHandler = new FileHandler("A_Types_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("anxietyCauses")){
            fileHandler = new FileHandler("A_CausesAndSymptoms_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("anxietyTreatment")){
            fileHandler = new FileHandler("A_Treatment_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("emotionalEating")){
            fileHandler = new FileHandler("EmotionalEating_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("emotionalEatingSigns")){
            fileHandler = new FileHandler("E_Signs_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("emotionalEatingCauses")){
            fileHandler = new FileHandler("E_Causes_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("emotionalEatingTreatment")){
            fileHandler = new FileHandler("E_Treatment_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("stress")){
            fileHandler = new FileHandler("Stress_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("stressTypes")){
            fileHandler = new FileHandler("S_Types_txt", this);
            fileHandler.readFile();
        }
        else if (category.equals("stressCauses")){
            fileHandler = new FileHandler("S_Causes_Symptoms_Complications_txt", this);
            fileHandler.readFile();
        }
        else if (category.equals("stressTreatment")){
            fileHandler = new FileHandler("S_Diagnosis_Treatment_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("depression")){
            fileHandler = new FileHandler("Depression_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("depressionTypes")){
            fileHandler = new FileHandler("D_Types_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("depressionCauses")){
            fileHandler = new FileHandler("D_Signs_Symptoms_Causes_txt", this);
            fileHandler.readFile();
        }
        else if(category.equals("depressionTreatment")){
            fileHandler = new FileHandler("D_Diagnosis_Treatment_txt", this);
            fileHandler.readFile();
        }
    }
}