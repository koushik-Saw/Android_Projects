package com.example.safety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safety.model.FireService;
import com.example.safety.model.Hospital;
import com.example.safety.model.PoliceStation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private EditText psName,psAddress,psPhone;
    private Button save;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        psName = findViewById(R.id.add_police_stationNameID);
        psAddress = findViewById(R.id.add_police_stationAddressID);
        psPhone = findViewById(R.id.add_police_stationNumberID);
        save = findViewById(R.id.saveID);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("fire service");
        save.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.saveID) {
            String name = psName.getText().toString();
            String address = psAddress.getText().toString();
            String phone = psPhone.getText().toString();
            String key = myRef.push().getKey();
            // FireService fs = new FireService(address,phone);
            FireService hos = new FireService(address,phone);

            myRef.child(key).setValue(hos);
            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
            psName.setText("");
            psAddress.setText("");
            psPhone.setText("");

        }

    }
}