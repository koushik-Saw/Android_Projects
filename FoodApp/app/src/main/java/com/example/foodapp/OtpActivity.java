package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {

    private EditText optnm;
    private Button verotp;
    private TextView errrr;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String mverificationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        optnm = findViewById(R.id.otpnum);
        verotp = findViewById(R.id.otp_ver);
        errrr = findViewById(R.id.otperr);
        mAuth = FirebaseAuth.getInstance();

        mverificationid = getIntent().getStringExtra("VerificationID");
        verotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otpnumb = optnm.getText().toString();

                if (otpnumb.isEmpty()) {
                    errrr.setVisibility(View.VISIBLE);
                    errrr.setText("Please fill the form");
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationid, otpnumb);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(OtpActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }
}