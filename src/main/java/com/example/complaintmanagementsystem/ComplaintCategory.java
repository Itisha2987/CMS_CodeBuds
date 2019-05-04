package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComplaintCategory extends AppCompatActivity {
    private static Button academic;
    private static Button infrastructure;
    private static Button municpal;
    private static Button health;
    private static Button transport;
    private static Button mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_category);

        academic = findViewById(R.id.academic);
        infrastructure = findViewById(R.id.infrastructure);
        municpal = findViewById(R.id.municpal);
        health = findViewById(R.id.health);
        transport = findViewById(R.id.transport);
        mess = findViewById(R.id.mess);

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    goToComplaint("Academic");
            }
        });

        infrastructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComplaint("Infrastructure");
            }
        });

        municpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComplaint("Municpal");
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComplaint("Health");
            }
        });

        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComplaint("Transport");
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToComplaint("Mess");
            }
        });
    }

    public void goToComplaint(String complaintType){
        Intent intent = new Intent(this, WriteComplaint.class);
        intent.putExtra("complaintType", complaintType);
        startActivity(intent);
    }
}
