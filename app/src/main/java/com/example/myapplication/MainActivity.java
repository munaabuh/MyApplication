package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    private Intent musicIntent;
    private Button profileButton;
    private CardView mentalHealthCard, mindfulnessCard;
    private RelativeLayout anxietyCard, anxietyCauses, anxietyTreatment, anxietyImpact,overthinkingCard, overthinkingCauses,
                           overthinkingTreatment,overthinkingImpact,stressCard, stressCauses,stressTreatment,stressImpact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton= findViewById(R.id.profileButton);

        mentalHealthCard= findViewById(R.id.mentalHealthCard);
        mentalHealthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category","mentalhealth");
                startActivity(i);
            }
        });

        mindfulnessCard =  findViewById(R.id.mindfulnessCard);
        mindfulnessCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "mindfulness");
                startActivity(i);
            }
        });

        anxietyCard= findViewById(R.id.anxietyCard);
        anxietyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category","anxiety");
                startActivity(i);
            }
        });

        anxietyCauses = findViewById(R.id.anxietyCauses);
        anxietyCauses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "anxietyCauses");
                startActivity(i);
            }
        });

        anxietyTreatment = findViewById(R.id.anxietyTreatment);
        anxietyTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "anxietyTreatment");
                startActivity(i);
            }
        });

        anxietyImpact = findViewById(R.id.anxietyImpact);
        anxietyImpact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "anxietyImpact");
                startActivity(i);

            }
        });

        overthinkingCard = findViewById(R.id.overthinkingCard);
        overthinkingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "overthinking");
                startActivity(i);
            }
        });

        overthinkingCauses = findViewById(R.id.overthinkingCauses);
        overthinkingCauses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "overthinkingCauses");
                startActivity(i);
            }
        });

        overthinkingTreatment = findViewById(R.id.overthinkingTreatment);
        overthinkingTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "overthinkingTreatment");
                startActivity(i);
            }
        });

        overthinkingImpact = findViewById(R.id.overthinkingImpact);
        overthinkingImpact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "overthinkingImpact");
                startActivity(i);
            }
        });

        stressCard= findViewById(R.id.stressCard);
        stressCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "stress");
                startActivity(i);
            }
        });

        stressCauses = findViewById(R.id.stressCauses);
        stressCauses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "stressCauses");
                startActivity(i);
            }
        });

        stressTreatment = findViewById(R.id.stressTreatment);
        stressTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "stressTreatment");
                startActivity(i);
            }
        });

        stressImpact = findViewById(R.id.stressImpact);
        stressImpact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("category", "stressImpact");
                startActivity(i);
            }
        });

        //this will start the service which in turn will start the music
        musicIntent = new Intent(this, MusicService.class);
        startService(musicIntent);

        Intent notifyIntent = new Intent(this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);


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

    public void profile(View view){

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }






}