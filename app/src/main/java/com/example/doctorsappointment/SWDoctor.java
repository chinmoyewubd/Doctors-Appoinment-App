package com.example.doctorsappointment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class SWDoctor extends AppCompatActivity {

    private ArrayList<Doctor> swrecords = new ArrayList<>();

    private CustomDoctorAdapter adapter;
    private ListView lvSWDoctor;
    private TextView tvspeciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swdoctor);
        lvSWDoctor = findViewById(R.id.lvSWDoctor);
        tvspeciality = findViewById(R.id.tvspeciality);

        adapter = new CustomDoctorAdapter(this,swrecords);
        lvSWDoctor.setAdapter(adapter);

        DoctorDB db = new DoctorDB(this);

        Bundle b = getIntent().getExtras();
        String value = b.getString("tag");
        System.out.println(value);


        String value2 = value.toUpperCase();

        tvspeciality.setText(value2);

        String q = "SELECT * FROM doctor WHERE speciality='"+value2+"'";


        Cursor c = db.selectDoctor(q);

        if(c!=null && c.getCount()>0){
            while(c.moveToNext()){
                String id = c.getString(0);
                String name = c.getString(1);
                String speciality = c.getString(2);
                String shr = c.getString(3);
                String ehr = c.getString(4);
                String contact = c.getString(5);

                Doctor doctor = new Doctor(id,name,speciality,shr,ehr,contact);

                swrecords.add(doctor);

            }
            System.out.println(swrecords);
        }else{
            Toast.makeText(this, "This type of Doctor is not available", Toast.LENGTH_SHORT).show();
        }
        db.close();
        adapter.notifyDataSetChanged();
        adapter.notifyDataSetInvalidated();


        lvSWDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor d =swrecords.get(position);

                System.out.println("from swdoctor");

                System.out.println(d);

                String did = d.id;
                System.out.println(did);
                String dname =d.name;
                String Speciality = d.speciality;
                String contact = d.contact;
                String time = d.shr;

                Bundle bundle = new Bundle();
                Intent I = new Intent(SWDoctor.this,fixDate.class);
                bundle.putString("did",did);
                bundle.putString("dname",dname);
                bundle.putString("Speciality",Speciality);
                bundle.putString("contact",contact);
                bundle.putString("time",time);
                I.putExtras(bundle);
                System.out.println("here befor startactivity swdoctor");
                startActivity(I);
            }
        });

    }
}