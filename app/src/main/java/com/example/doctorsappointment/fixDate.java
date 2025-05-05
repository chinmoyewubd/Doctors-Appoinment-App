package com.example.doctorsappointment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class fixDate extends AppCompatActivity {

    private TextView tvName, tvSpeciality, tvContact, tvTime;
    private EditText editTextAppFullName, editTextAppAddress, editTextAppContactNumber;
    private Button buttonAppDate, buttonBookAppointment;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_date);

        tvName = findViewById(R.id.tvName);
        tvSpeciality = findViewById(R.id.tvSpeciality);
        tvContact = findViewById(R.id.tvContact);
        tvTime = findViewById(R.id.tvTime);


        editTextAppFullName = findViewById(R.id.editTextAppFullName);
        editTextAppAddress = findViewById(R.id.editTextAppAddress);
        editTextAppContactNumber = findViewById(R.id.editTextAppContactNumber);

        buttonAppDate = findViewById(R.id.buttonAppDate);
        buttonBookAppointment = findViewById(R.id.buttonBookAppointment);


        //databse store initialization

        databaseReference = FirebaseDatabase.getInstance().getReference("Appointment");


        Bundle  bundle = getIntent().getExtras();

        String did = bundle.getString("did");
        String dname = bundle.getString("dname");
        String speciality = bundle.getString("Speciality");
        String contact = bundle.getString("contact");
        String time = bundle.getString("time");

        tvName.setText(dname);
        tvSpeciality.setText(speciality);
        tvContact.setText(contact);
        tvTime.setText(time);
        System.out.println("here");
        System.out.println(did);


        buttonAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = buttonAppDate.getText().toString();
                int year, month, date;
                if(selectedDate.equals("DD-MM-YYYY")){
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    date = c.get(Calendar.DATE);
                } else {
                    String[] dateParts = selectedDate.split("-");
                    date = Integer.parseInt(dateParts[0]);
                    month = Integer.parseInt(dateParts[1]) - 1;
                    year = Integer.parseInt(dateParts[2]);
                }
                DatePickerDialog dpd =new DatePickerDialog(fixDate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        buttonAppDate.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);

                        String date = buttonAppDate.getText().toString();
                        long selectedDate = getCurrentDateInMilliseconds(date);

                        storeInServer(did,selectedDate);



                    }
                }, year, month, date);
                dpd.show();
            }
        });

        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(fixDate.this,UpcommingAppointment.class);

                startActivity(i);

            }
        });

    }

    private void storeInServer(String did,long selectedDate) {
        String pname = editTextAppFullName.getText().toString().trim();
        String address = editTextAppAddress.getText().toString().trim();
        String pcontact = editTextAppContactNumber.getText().toString().trim();

        Appointment appointment = new Appointment(did,did+10,pname,address,pcontact,selectedDate);

        databaseReference.child(appointment.doctoid).setValue(appointment);


    }

    private long getCurrentDateInMilliseconds(String selectedDate) {
        if(selectedDate.equals("DD-MM-YYYY")){
            return 0;
        }
        String[] dateParts = selectedDate.split("-");
        int date = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1;
        int year = Integer.parseInt(dateParts[2]);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(0);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, date);
        long timeInMilliSeconds = c.getTimeInMillis();
        return timeInMilliSeconds;
    }
}