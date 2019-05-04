package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.complaintmanagementsystem.Models.User.Staff;
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StaffRegistration extends AppCompatActivity {
    private static String name;
    private static String email;
    private static String pass;
    private static String department;
    private TextInputLayout textDesignation;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_registration);

        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBarStaff);


        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        email = intent.getExtras().getString("email");
        pass = intent.getExtras().getString("pass");

        textDesignation=findViewById(R.id.designation);
        Spinner spinner1 = (Spinner) findViewById(R.id.department);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                department = parent.getItemAtPosition(pos).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });


    }
    public void staffRegistration(View v){
        String designation=textDesignation.getEditText().getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        if(designation.isEmpty())
        {
            textDesignation.setError("Field cannot be empty");
            return;
        }
        else{
            textDesignation.setError(null);
        }

        final Staff staff = new Staff( department,  designation);
        Log.e("Pass", pass);
        Log.e("name", name);
        Log.e("email", email);
        Log.e("role", String.valueOf(3));
        Log.e("department", department);
        Log.e("designation", designation);
        final User user=new User(name,email,3,"");

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful())
                                    {
                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                                child("type").setValue(staff).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(StaffRegistration.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(getBaseContext(),Home.class));
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(StaffRegistration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(StaffRegistration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
