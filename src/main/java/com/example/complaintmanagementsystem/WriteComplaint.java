package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.complaintmanagementsystem.Models.Complaint.Complaint;
import com.example.complaintmanagementsystem.utils.Helper;

import org.w3c.dom.Text;

public class WriteComplaint extends AppCompatActivity {
    private static String complaintType;
    private static Button submit;
    private static String complaintDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complaint);

        Intent intent = getIntent();
        complaintType = intent.getExtras().getString("complaintType");




        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComplaint();
            }
        });
    }

    private void submitComplaint(){
        TextInputLayout complaintDetailsText= findViewById(R.id.complaintDetails);
        complaintDetails = complaintDetailsText.getEditText().getText().toString();

        if(complaintDetails.isEmpty()){
            complaintDetailsText.setError("Field cannot be empty");
        }
        else if(complaintDetails.length() < 20){
            complaintDetailsText.setError("Minimum 20 characters");
        }
        else if(complaintDetails.length() > 1000) {
            complaintDetailsText.setError("Maximum 30 characters");
        }
        Complaint newComplaint = new Complaint(complaintType, complaintDetails);
        Log.e("s:", "SubmitComplaint");
        Log.e("Cab:", complaintDetails);
        Log.e("Type", complaintType);
        Log.e("status","Pending");
        Log.e("date", newComplaint.getDate());
    }
}
