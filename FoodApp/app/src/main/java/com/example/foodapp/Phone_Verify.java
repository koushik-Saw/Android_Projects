package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Phone_Verify extends AppCompatActivity {

    private Button otpbtn;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText code,phone;
    private TextView error;

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__verify);

        otpbtn = findViewById(R.id.otp_gen);
        phone = findViewById(R.id.phonenumber);
        error = findViewById(R.id.err);
        mAuth = FirebaseAuth.getInstance();

        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phn = phone.getText().toString();
                String complete_number = phn;
                if(phn.isEmpty()){
                    error.setVisibility(View.VISIBLE);
                    error.setText("Please fill the form");
                }
                else{
                    error.setVisibility(View.INVISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        complete_number,
                            60,
                            TimeUnit.SECONDS,
                            Phone_Verify.this,
                            mcallbacks
                    );
                }
            }
        });

        mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Intent i = new Intent(Phone_Verify.this,OtpActivity.class);
                i.putExtra("VerificationID",s);
                startActivity(i);
            }
        };

    }
}