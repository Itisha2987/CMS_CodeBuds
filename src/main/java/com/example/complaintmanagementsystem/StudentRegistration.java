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

import com.example.complaintmanagementsystem.Models.User.Student;
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegistration extends AppCompatActivity {
    private static String faculty = "Computer Engg";
    private static String hostel = "Hostel-1";
    private static String name;
    private static String email;
    private static String pass;
    private static boolean isSingle=true;

    private TextInputLayout textRegNo;
    private TextInputLayout textRoomNo;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        mAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBarStudent);
        textRegNo=findViewById(R.id.regNo);
        textRoomNo=findViewById(R.id.roomNo);

        Spinner spinner1 = (Spinner) findViewById(R.id.hostelNo);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.hostelNoArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);


        Spinner spinner2 = (Spinner) findViewById(R.id.faculty);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.facultyArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);


        Spinner spinner3 = (Spinner) findViewById(R.id.roomType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.roomType, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                hostel = parent.getItemAtPosition(pos).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                faculty = parent.getItemAtPosition(pos).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String singleRoom = parent.getItemAtPosition(pos).toString();
                if(singleRoom != "Double Room"){
                    isSingle = true;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        email = intent.getExtras().getString("email");
        pass = intent.getExtras().getString("pass");
    }

    public void registerStudent(View v)
    {
        progressBar.setVisibility(View.VISIBLE);
        String value= textRegNo.getEditText().getText().toString();
        String room = textRoomNo.getEditText().getText().toString();

        if(value.isEmpty())
        {
            textRegNo.setError("Field cannot be empty");
            return;
        }
        else{
            textRegNo.setError(null);
        }
        if(room.isEmpty())
        {
            textRoomNo.setError("Field cannot be empty");
            return;
        }
        else{
            textRoomNo.setError(null);
        }
        final int regNo=Integer.parseInt(value);
        final int roomNo=Integer.parseInt(room);

        Log.e("pass", pass);
        Log.e("name", name);
        Log.e("email", email);
        Log.e("role", String.valueOf(1));
        Log.e("roomNo", String.valueOf(roomNo));
        Log.e("faculty", faculty);
        Log.e("regNo", String.valueOf(regNo));
        Log.e("isSingle", String.valueOf(isSingle));
        Log.e("Hostel No",hostel);
        final User user=new User(name,email,1,"");
        final Student student = new Student(hostel,roomNo,faculty,regNo,isSingle);

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
                                                child("type").setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(StudentRegistration.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(getBaseContext(),Home.class));
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(StudentRegistration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(StudentRegistration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
