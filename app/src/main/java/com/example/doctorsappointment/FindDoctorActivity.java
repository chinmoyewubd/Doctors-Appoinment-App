package com.example.doctorsappointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctorActivity extends AppCompatActivity {

    private CardView cardFDGeneral, cardFDNutrition, cardFDPediatrics, cardFDDentist, cardFDSurgeon,
            cardFDEye, cardFDCardiologists, cardFDBone, cardNerv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        cardFDGeneral = findViewById(R.id.cardFDGeneral);
        cardFDNutrition = findViewById(R.id.cardFDNutrition);
        cardFDPediatrics = findViewById(R.id.cardFDPediatrics);
        cardFDDentist = findViewById(R.id.cardFDDentist);
        cardFDSurgeon = findViewById(R.id.cardFDSurgeon);
        cardFDEye = findViewById(R.id.cardFDEye);
        cardFDCardiologists = findViewById(R.id.cardFDCardiologists);
        cardFDBone = findViewById(R.id.cardFDBone);
        cardNerv = findViewById(R.id.cardNerv);

        cardFDGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);

                i.putExtra("tag","general");
                startActivity(i);
            }
        });

        cardFDBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Orthopedics");
                startActivity(i);
            }
        });
        cardFDNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Nutrition");
                startActivity(i);
            }
        });
        cardFDPediatrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Pediatrics");
                startActivity(i);
            }
        });
        cardFDDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Dentist");
                startActivity(i);
            }
        });
        cardFDSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Surgeon");
                startActivity(i);
            }
        });
        cardFDEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Eye");
                startActivity(i);
            }
        });
        cardNerv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Nerv");
                startActivity(i);
            }
        });

        cardFDDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Dentist");
                startActivity(i);
            }
        });

        cardFDCardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FindDoctorActivity.this,SWDoctor.class);
                i.putExtra("tag","Cardiologists");
                startActivity(i);
            }
        });

    }
}