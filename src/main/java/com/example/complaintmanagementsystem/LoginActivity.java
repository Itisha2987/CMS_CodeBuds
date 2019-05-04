package com.example.complaintmanagementsystem;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textEmailAddress;
    private TextInputLayout textPassword;
    private Button logIn;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmailAddress=findViewById(R.id.text_emailAddress);
        textPassword=findViewById(R.id.text_password);
        logIn=findViewById(R.id.LLogin);
        firebaseAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUserID() | !validatePassword()){
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                loginUser();

            }
        });
    }
    private boolean validateUserID(){
        String emailAddress=textEmailAddress.getEditText().getText().toString().trim();
        if(emailAddress.isEmpty())
        {
            textEmailAddress.setError("Field cannot be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            textEmailAddress.setError("Please enter a valid email address");
            return false;
        }
        else{
            textEmailAddress.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String uID=textPassword.getEditText().getText().toString().trim();
        if(uID.isEmpty())
        {
            textPassword.setError("Field cannot be empty");
            return false;
        }
        else{
            textPassword.setError(null);
            return true;
        }
    }
    private void loginUser()
    {
        String userID=textEmailAddress.getEditText().getText().toString().trim();
        String password=textPassword.getEditText().getText().toString();

        firebaseAuth.signInWithEmailAndPassword(userID,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            finish();
                            Intent intent=new Intent(getBaseContext(),Home.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
