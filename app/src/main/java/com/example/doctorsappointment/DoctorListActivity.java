package com.example.doctorsappointment;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {

    public Button btnLoad,btnAddDoctor,btnSpecialityWise;
    public ListView lvDoctor;


    private ArrayList<Doctor> records = new ArrayList<>();
    private CustomDoctorAdapter adapter;
    private boolean shouldUpdate = false;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        btnLoad = findViewById(R.id.btnLoad);
        btnAddDoctor = findViewById(R.id.btnAddDoctor);
        lvDoctor = findViewById(R.id.lvDoctor);
        btnSpecialityWise = findViewById(R.id.btnSpecialityWise);
        databaseReference = FirebaseDatabase.getInstance().getReference("DoctorsInfo");
        adapter = new CustomDoctorAdapter(this,records);
        lvDoctor.setAdapter(adapter);


        lvDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor d =records.get(position);

                System.out.println("from doctorlist");

                System.out.println(d);

                String did = d.id;
                System.out.println(did);
                String dname =d.name;
                String Speciality = d.speciality;
                String contact = d.contact;
                String time = d.shr;

                Bundle bundle = new Bundle();
                Intent I = new Intent(DoctorListActivity.this,fixDate.class);
                bundle.putString("did",did);
                bundle.putString("dname",dname);
                bundle.putString("Speciality",Speciality);
                bundle.putString("contact",contact);
                bundle.putString("time",time);
                I.putExtras(bundle);
                startActivity(I);

            }
        });




        btnSpecialityWise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DoctorListActivity.this,FindDoctorActivity.class);
                startActivity(i);


            }
        });


       btnAddDoctor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(DoctorListActivity.this, AddDoctorActivity.class);
               startActivity(i);
           }
       });



       btnLoad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(loadDoctorFromLocalDB()){
                   updateServerWithLocalData();
               }else{
                   updateLocalDBByServerData();
               }
           }
       });

    }
    private boolean loadDoctorFromLocalDB() {


        records.clear();


        // if a valid date is selected, then load data from db for that date
        String q = "SELECT * FROM doctor";
        DoctorDB db = new DoctorDB(this);
        Cursor c = db.selectDoctor(q);
        System.out.println("at column"+c.getCount());
        System.out.println(c.getCount()); //1734480000000 1734480000000
        if(c!=null && c.getCount()>0) {


            while (c.moveToNext()) {
                String id = c.getString(0).trim();
                String name = c.getString(1).trim();
                String speciality = c.getString(2).trim();
                String shr = c.getString(3).trim();
                String ehr = c.getString(3).trim();
                String contact =c.getString(5).trim();
                Doctor d = new Doctor(id, name, speciality,shr,ehr,contact);
                System.out.println("from if");

                System.out.println(d.name + " " + d.speciality);
                records.add(d);
            }
            db.close();
            System.out.println(records);

            adapter.notifyDataSetChanged();
            adapter.notifyDataSetInvalidated();

            return true;

        }

            return false;
    }

    private void updateServerWithLocalData(){

        for(Doctor d :records){

            databaseReference.child(d.id).setValue(d);
        }
    }

    private void updateLocalDBByServerData(){
        System.out.println("found"  );
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Doctor d = snapshot1.getValue(Doctor.class);
                    DoctorDB db = new DoctorDB(DoctorListActivity.this);
                    db.insertDoctor(d.id,d.name,d.speciality,d.shr,d.ehr,d.contact);

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DoctorListActivity.this, "NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
