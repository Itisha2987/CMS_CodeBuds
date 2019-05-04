package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void StudentRegistration(View v)
    {
        Intent intent = new Intent(this, UserRegistration.class);
        intent.putExtra("Role", 1);
        finish();
        startActivity(intent);
    }

    public void professorRegistration(View v)
    {
        Intent intent = new Intent(this, UserRegistration.class);
        intent.putExtra("Role", 2);
        finish();
        startActivity(intent);
    }

    public void staffRegistration(View v){
        Intent intent = new Intent(this, UserRegistration.class);
        intent.putExtra("Role", 3);
        finish();
        startActivity(intent);
    }
}
