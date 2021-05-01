package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Instant;

public class SignUp extends AppCompatActivity{

    private EditText signupEmail,signupPassword;
    private Button signupButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);

        mAuth = FirebaseAuth.getInstance();
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String suemail = signupEmail.getText().toString();
                String supassword = signupPassword.getText().toString();
                if (!suemail.isEmpty() && !supassword.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(SignUp.this,LoginActivity.class);
                                startActivity(i);
                            }
                            else {

                                Toast.makeText(SignUp.this, "Password at least 6 Character", Toast.LENGTH_SHORT).show();
                                signupPassword.setText("");
                                signupEmail.setText("");
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUp.this, "Enter Sign Up details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}