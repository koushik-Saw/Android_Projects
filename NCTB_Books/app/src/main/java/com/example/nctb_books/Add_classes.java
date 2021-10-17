package com.example.nctb_books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nctb_books.Model.Classes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_classes extends AppCompatActivity {

    EditText additm,addno;
    Button addbt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classes);

        additm = findViewById(R.id.additems);
        addno = findViewById(R.id.addno);
        addbt = findViewById(R.id.addbtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("Classes");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("my peff",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Hellp","Heloo");
        editor.commit();

        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String items = additm.getText().toString().trim();
                String no = addno.getText().toString().trim();
                String key = no;
                Classes classes = new Classes(items);
                databaseReference.child(key).setValue(classes);
                Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }
}