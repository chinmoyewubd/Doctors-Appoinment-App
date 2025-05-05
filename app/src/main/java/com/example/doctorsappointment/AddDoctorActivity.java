package com.example.doctorsappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddDoctorActivity extends AppCompatActivity {

    private long selectedStartTimeInMillis;
    private long EndTimeInMillis;

    private EditText etId,etName,etSpeciality,etContact;

    private Button btnSave,btnStartTime,btnEndTime;

    private ListView lvDoctor;

    private String course = "CSE489";
    private int section = 2;

    private String selectedAction = null;
    private ArrayList<Doctor> records = new ArrayList<>();
    private CustomDoctorAdapter adapter;
    private boolean shouldUpdate = false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etSpeciality = findViewById(R.id.etSpeciality);
        btnEndTime = findViewById(R.id.btnEndTime);
        etContact = findViewById(R.id.etContact);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndTime = findViewById(R.id.btnEndTime);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                String name = etName.getText().toString().trim().toUpperCase();
                String speciality = etSpeciality.getText().toString().trim().toUpperCase();
                String consultationstart = btnStartTime.getText().toString().trim();
                String consultationend = btnEndTime.getText().toString().trim();
                String contact = etContact.getText().toString().trim();

                String stm =Long.toString(selectedStartTimeInMillis);
                String etm =Long.toString(EndTimeInMillis);

                System.out.println(id);
                System.out.println(name);
                System.out.println(speciality);
                System.out.println(consultationstart);
                System.out.println(contact);
                if(name.length() < 4){
                    Toast.makeText(AddDoctorActivity.this, "User name should be 4-8 characters", Toast.LENGTH_LONG).show();
                    return;
                }


                DoctorDB db = new DoctorDB(AddDoctorActivity.this);
                db.insertDoctor(id,name,speciality,consultationstart,consultationend,contact);
                System.out.println("success insert db");


                db.close();

                etId.setText("");
                etName.setText("");
                etSpeciality.setText("");
                btnStartTime.setText("");
                btnEndTime.setText("");
                etContact.setText("");

            }
        });

        btnStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Default time values
                int hour, minute;

                String selectedTime = btnStartTime.getText().toString();
                if (selectedTime.equals("HH:MM")) {
                    // Get current time if no time is selected
                    Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                } else {
                    // Parse the selected time
                    String[] timeParts = selectedTime.split(":");
                    hour = Integer.parseInt(timeParts[0]);
                    minute = Integer.parseInt(timeParts[1]);
                }

                // Open TimePickerDialog
                TimePickerDialog tpd = new TimePickerDialog(AddDoctorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format and display selected time on the button
                        String formattedTime = String.format("%02d:%02d", hourOfDay, minute);
                        btnStartTime.setText(formattedTime);

                        // Convert time to milliseconds if needed
                     long selectedTimeInMillis = getTimeInMilliseconds(hourOfDay, minute);

                        selectedStartTimeInMillis=selectedTimeInMillis;

                        // Example: Use the time in your logic
                        System.out.println(selectedTimeInMillis);
                    }
                }, hour, minute, true); // Use true for 24-hour format, false for 12-hour format
                tpd.show();
            }
        });

        btnEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Default time values
                int hour, minute;

                String selectedTime = btnEndTime.getText().toString();
                if (selectedTime.equals("HH:MM")) {
                    // Get current time if no time is selected
                    Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                } else {
                    // Parse the selected time
                    String[] timeParts = selectedTime.split(":");
                    hour = Integer.parseInt(timeParts[0]);
                    minute = Integer.parseInt(timeParts[1]);
                }

                // Open TimePickerDialog
                TimePickerDialog tpd = new TimePickerDialog(AddDoctorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format and display selected time on the button
                        String formattedTime = String.format("%02d:%02d", hourOfDay, minute);
                        btnEndTime.setText(formattedTime);

                        // Convert time to milliseconds if needed
                        long selectedTimeInMillis = getTimeInMilliseconds(hourOfDay, minute);

                        EndTimeInMillis=selectedTimeInMillis;

                        // Example: Use the time in your logic
                        System.out.println(selectedTimeInMillis);
                    }
                }, hour, minute, true); // Use true for 24-hour format, false for 12-hour format
                tpd.show();
            }
        });





    }
    private long getTimeInMilliseconds(int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * Example method to send selected time to the server.
     */
    private void storeTimeInServer(String did, long selectedTime) {
        System.out.println("Doctor ID: " + did);
        System.out.println("Selected Time in Milliseconds: " + selectedTime);
    }



}