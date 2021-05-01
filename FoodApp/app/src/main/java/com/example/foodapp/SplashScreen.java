package com.example.foodapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreen extends AppCompatActivity {

    private TextView splashScreenTV;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        splashScreenTV = findViewById(R.id.splashScreenTV);

        Typeface customFont = Typeface.createFromAsset(getAssets(),"fonts/Adamina-Regular.ttf");
        splashScreenTV.setTypeface(customFont);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                loginActivity();
            }
        });

        thread.start();


        }

    private void loginActivity() {
        finish();
        startActivity(new Intent(SplashScreen.this,LoginActivity.class));
        /*if(FirebaseAuth.getInstance().getCurrentUser() == null){
            finish();
            startActivity(new Intent(SplashScreen.this,LoginActivity.class));
        }else {
            finish();
            startActivity(new Intent(SplashScreen.this,MainActivity.class));
        }*/

    }

    private void doWork() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
