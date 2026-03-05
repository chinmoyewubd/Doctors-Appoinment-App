package com.example.doctorsappointment;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpcommingAppointment extends AppCompatActivity {
    DatabaseReference databaseReference;
    private ListView lvAppointments;

    private ArrayList<Doctor> drecords = new ArrayList<>();
    private CustomDoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomming_appointment);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        lvAppointments = findViewById(R.id.lvSWDoctor);
        adapter = new CustomDoctorAdapter(this, drecords);
        lvAppointments.setAdapter(adapter);

        loadUpcomingAppointments();
    }

    private void loadUpcomingAppointments() {
        // Clear previous data
        drecords.clear();

        databaseReference = FirebaseDatabase.getInstance().getReference("Appointment");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> doctorIds = new ArrayList<>();

                // First, collect all unique doctor IDs from appointments
                for (DataSnapshot appointmentSnapshot : snapshot.getChildren()) {
                    Appointment appointment = appointmentSnapshot.getValue(Appointment.class);
                    if (appointment != null && !doctorIds.contains(appointment.doctoid)) {
                        doctorIds.add(appointment.doctoid);
                    }
                }

                // Then, fetch doctor details for each unique ID
                DoctorDB db = new DoctorDB(UpcommingAppointment.this);
                for (String doctorId : doctorIds) {
                    String query = "SELECT * FROM doctor WHERE ID = '" + doctorId + "'";
                    Cursor cursor = db.selectDoctor(query);

                    if (cursor != null && cursor.moveToFirst()) {
                        String id = cursor.getString(0).trim();
                        String name = cursor.getString(1).trim();
                        String speciality = cursor.getString(2).trim();
                        String shr = cursor.getString(3).trim();
                        String ehr = cursor.getString(4).trim();
                        String contact = cursor.getString(5).trim();

                        Doctor doctor = new Doctor(id, name, speciality, shr, ehr, contact);
                        drecords.add(doctor);
                        cursor.close();
                    }
                }
                db.close();

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpcommingAppointment.this, "Failed to load appointments", Toast.LENGTH_SHORT).show();
            }
        });
    }
}