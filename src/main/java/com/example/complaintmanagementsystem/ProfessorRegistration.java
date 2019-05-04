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

import com.example.complaintmanagementsystem.Models.User.Professor;
import com.example.complaintmanagementsystem.Models.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ProfessorRegistration extends AppCompatActivity {
    private static String designation="Dean";
    private static String faculty="Computer Engg";

    private static String name;
    private static String email;
    private static String pass;
    private TextInputLayout officeNo;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_registration);
        progressBar=findViewById(R.id.progressBarProfessor);
        mAuth=FirebaseAuth.getInstance();

        officeNo=findViewById(R.id.officeNo);
        Spinner spinner1 = (Spinner) findViewById(R.id.designation);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.designationProfessor, android.R.layout.simple_spinner_item);
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


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                designation = parent.getItemAtPosition(pos).toString();
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

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        email = intent.getExtras().getString("email");
        pass = intent.getExtras().getString("pass");

    }

    public void professorRegistration(View v){

        progressBar.setVisibility(View.VISIBLE);
        String oNo=officeNo.getEditText().getText().toString();
        if(oNo.isEmpty())
        {
            officeNo.setError("Field cannot be empty");
            return;
        }
        else
        {
            officeNo.setError(null);
        }

        final Professor professor = new Professor(faculty,designation,oNo);
        Log.e("pass", pass);
        Log.e("name", name);
        Log.e("email", email);
        Log.e("role", String.valueOf(2));
        Log.e("faculty", faculty);
        Log.e("designation", designation);
        Log.e("Office No", oNo);
        final User user=new User(name,email,2,"");

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
                                                child("type").setValue(professor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ProfessorRegistration.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(getBaseContext(),Home.class));
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(ProfessorRegistration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(ProfessorRegistration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
