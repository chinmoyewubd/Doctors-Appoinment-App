package com.example.doctorsappointment;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpcommingAppointment extends AppCompatActivity {
    DatabaseReference databaseReference;
    private ListView lvSWDoctor;

    private ArrayList<Appointment> records = new ArrayList<>();
    private ArrayList<String> r = new ArrayList<>();
    private CustomDoctorAdapter adapter;
    private ArrayList<Doctor> drecords = new ArrayList<>();

    Appointment a = new Appointment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomming_appointment);

        // Initialize ListView
        lvSWDoctor = findViewById(R.id.lvSWDoctor);

        // Initialize adapter with drecords
        adapter = new CustomDoctorAdapter(this, drecords);
        lvSWDoctor.setAdapter(adapter);

        loadUpcommingAppointments();
    }

    private void loadUpcommingAppointments() {
        System.out.println("found");

        databaseReference = FirebaseDatabase.getInstance().getReference("Appointment");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    // Get Appointment object
                    Appointment a = snapshot1.getValue(Appointment.class);

                    // Add doctor ID to the list
                    r.add(a.doctoid);
                }

                // Fetch doctor details based on the doctoid
                for (String s : r) {
                    String q = "SELECT * FROM doctor";
                    DoctorDB db = new DoctorDB(UpcommingAppointment.this);
                    Cursor c = db.selectDoctor(q);
                    System.out.println("at column" + c.getCount());

                    if (c != null && c.getCount() > 0) {
                        while (c.moveToNext()) {
                            String id = c.getString(0).trim();
                            String name = c.getString(1).trim();
                            String speciality = c.getString(2).trim();
                            String shr = c.getString(3).trim();
                            String ehr = c.getString(4).trim(); // Corrected index for 'ehr'
                            String contact = c.getString(5).trim();

                            Doctor d = new Doctor(id, name, speciality, shr, ehr, contact);
                            if (s.equals(id)) { // Use equals() to compare strings
                                drecords.add(d);
                            }
                        }
                        db.close();

                        // Notify adapter about data change
                        adapter.notifyDataSetChanged();
                        adapter.notifyDataSetInvalidated();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(UpcommingAppointment.this, "NOT FOUND", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
