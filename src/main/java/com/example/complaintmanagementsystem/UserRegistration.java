package com.example.complaintmanagementsystem;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.complaintmanagementsystem.Models.User.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration extends AppCompatActivity {
    private static int role;
    private TextInputLayout textName;
    private TextInputLayout textEmail;
    private TextInputLayout textPassword;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Intent intent = getIntent();

        textEmail=findViewById(R.id.email);
        textName=findViewById(R.id.name);
        textPassword=findViewById(R.id.text_password);
        imageView=findViewById(R.id.signupImage);
        role = intent.getExtras().getInt("Role");
        if(role==1)
        {
            imageView.setImageResource(R.drawable.student);
        }
        else if(role==2)
        {
            imageView.setImageResource(R.drawable.professor);
        }
        else
        {
            imageView.setImageResource(R.drawable.staff);
        }
    }

    public void continueRegistration(View v)
    {
        String name=textName.getEditText().getText().toString();
        String email=textEmail.getEditText().getText().toString().trim();
        String pass=textPassword.getEditText().getText().toString();

        if(isValidName(name) && isValidPassword(pass) && isValidEmail(email))
        {
            if(role == 1) {
                Log.e("User", "User Registration");
                Intent intent = new Intent(this, StudentRegistration.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }

            if(role == 2) {
                Log.e("Profes", "Prof Registration");
                Intent intent = new Intent(this, ProfessorRegistration.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }


            if(role == 3) {
                Intent intent = new Intent(this, StaffRegistration.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        }
    }




    //Helpers Function
    // validating email id
    private boolean isValidEmail(String email) {
        if(email.isEmpty()){
            textEmail.setError("Field cannot be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            textEmail.setError("Invalid email address");
            return false;
        }
        textEmail.setError(null);
        return true;
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if(pass.isEmpty()){
            textPassword.setError("Field cannot be empty");
            return false;
        }
        else if(pass.length()<6)
        {
            textPassword.setError("Password should be at least six characters");
            return false;
        }
        else{
            textPassword.setError(null);
            return true;
        }
    }
    //name is not empty
    private boolean isValidName(String name)
    {
        if(name.isEmpty()){
            textName.setError("Field cannot be empty");
            return false;
        }
        textName.setError(null);
        return true;
    }

}
