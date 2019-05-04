package com.example.complaintmanagementsystem;

import android.app.ProgressDialog;
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


import com.example.complaintmanagementsystem.MainHelpers.GMailSender;


import org.w3c.dom.Text;

public class WriteComplaint extends AppCompatActivity {
    private static String complaintType;
    private static Button btnSend;
    private static String complaintDetails;
    EditText etContent, etRecipient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_complaint);

        Intent intent = getIntent();
        complaintType = intent.getExtras().getString("complaintType");


        etContent = (EditText) findViewById(R.id.etContent);
        etRecipient = (EditText)findViewById(R.id.etRecipient);

        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void submitComplaint(){

    }

    private void sendMessage() {

        final ProgressDialog dialog = new ProgressDialog(WriteComplaint.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("cmsiitj2019@gmail.com", "cms@cs223");
                    sender.sendMail("EmailSender App",
                            etContent.getText().toString(),
                            "youremailaddress",
                            etRecipient.getText().toString());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}



