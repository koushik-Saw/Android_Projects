package com.example.collageproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collageproject.models.Courses;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add extends AppCompatActivity {

    EditText facyr,faculty,sub,yr;
    Button button;
    DatabaseReference databaseReference;
    String a,b,c,d,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        databaseReference = FirebaseDatabase.getInstance().getReference("subjects");
        facyr = findViewById(R.id.fac_yr);
        faculty = findViewById(R.id.faculty);
        sub = findViewById(R.id.add_sub);
        yr = findViewById(R.id.Year);
        button = findViewById(R.id.btnd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = facyr.getText().toString().trim();
                b = faculty.getText().toString().trim();
                c = sub.getText().toString().trim();
                d = yr.getText().toString().trim();

                String key = a+c;
                Courses courses = new Courses(b,d,c,a);
                databaseReference.child(key).setValue(courses);
                Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();

//                e = c;
//                String key= a+b+e;
//
//                Courses courses = new Courses(b,d);
//                databaseReference.child(key).setValue(courses);
//                Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
            }
        });

    }
}