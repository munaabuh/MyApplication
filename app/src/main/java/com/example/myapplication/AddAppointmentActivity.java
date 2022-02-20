package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddAppointmentActivity extends AppCompatActivity {

    private Date date;
    private Time time;
    private Button add;
    DatabaseReference myRef;
    private EditText date_time,appointmentBody;
    private Appointment a = new Appointment();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://sanctum-bc758-default-rtdb.europe-west1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        String [] therapists = new String[]{"Elisabeth Wajnryt", "Orli Jacobs", "Wendy Gordon", "Liat Gamzo", "Natasha Miller Gutman", "Jennifer Trugeman"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.drop_down_item, therapists);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.therapistName);
        autoCompleteTextView.setAdapter(adapter);


        String user = FirebaseAuth.getInstance().getUid();
        myRef = database.getReference("users/" + user + "/Appointments");
        add = findViewById(R.id.add);


        appointmentBody = findViewById(R.id.appointmentBody);
        date_time = findViewById(R.id.date_time);
        date_time.setInputType(InputType.TYPE_NULL);
        date_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(date_time);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AppointmentListActivity.class);
                //a = new Appointment(date.toString(), time.toString(), therapistName.getText().toString(), appointmentBody.getText().toString());
                //Log.d("REMINDER: ",r.toString());
                myRef.push().setValue(a);
                startActivity(i);
            }
        });

    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                date = new Date(year, month, dayOfMonth);
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                        time = new Time(hourOfDay, minute, 0);
                    }
                };

                new TimePickerDialog(AddAppointmentActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }

        };

        new DatePickerDialog(AddAppointmentActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}