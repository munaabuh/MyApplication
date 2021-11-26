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
    private RelativeLayout anxietyCard, overthinkingCard, stressCard, depressionCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.profileButton);
        findViewById(R.id.mindfulnessCard);
        findViewById(R.id.mentalHealthCard);

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

    public void mentalHealth(View view){

        Intent intent = new Intent(this, MentalHealthActivity.class);
        startActivity(intent);

    }

   public void  mindfulness(View view){

        Intent intent = new Intent(this,MindfulnessActivity.class);
        startActivity(intent);

   }

   public void anxiety(View view){

        Intent intent = new Intent(this,AnxietyActivity.class);
        startActivity(intent);

   }

   public void overthinking(View view){

        Intent intent = new Intent(this, OverthinkingActivity.class);
        startActivity(intent);

   }

   public void stress(View view){

        Intent intent = new Intent(this, StressActivity.class);
        startActivity(intent);

   }

   public void depression(View view){

        Intent intent = new Intent(this, DepressionActivity.class);
        startActivity(intent);

   }

}