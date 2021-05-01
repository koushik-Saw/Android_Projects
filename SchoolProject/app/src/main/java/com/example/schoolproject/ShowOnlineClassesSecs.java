package com.example.schoolproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Adapters.ShowOnlineClassSecAdapter;
import com.example.schoolproject.model.Sections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowOnlineClassesSecs extends AppCompatActivity implements ShowOnlineClassSecAdapter.onOnlineclasssecListener {
    DatabaseReference databaseReference;
    RecyclerView section_recyclerView;
    ArrayList<Sections> onlineClasses;
    ShowOnlineClassSecAdapter showOnlineClassSecAdapter;
    String a;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_online_classes);
        toolbar = findViewById(R.id.toolbartitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Online Classes");
        Intent intent = getIntent();
        a = intent.getStringExtra("Class_Name");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Sections");
        section_recyclerView = findViewById(R.id.showclassesec_recycleId);
        onlineClasses = new ArrayList<Sections>();
        showOnlineClassSecAdapter = new ShowOnlineClassSecAdapter(onlineClasses, this);
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlineClasses.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        onlineClasses.add(dataSnapshot1.getValue(Sections.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (showOnlineClassSecAdapter != null) {
                        showOnlineClassSecAdapter.getFilter().filter(a);
                        showOnlineClassSecAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                section_recyclerView.setAdapter(showOnlineClassSecAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    TextView classnm, classsc;
    String links, links2, links3;

    @Override
    public void onClickItem(int position) {
        onlineClasses.get(position);

        classnm = section_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.sample_online_cnlas_sec_names);
        classsc = section_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.sample_online_classSec);
        links = classnm.getText().toString().trim();
        links2 = classsc.getText().toString().trim();

        if (links.equals("Pre-Primary")) {
            links3 = "0";
        } else if (links.equals("Class One")) {
            links3 = "1";
        } else if (links.equals("Class Two")) {
            links3 = "2";
        } else if (links.equals("Class Three")) {
            links3 = "3";
        } else if (links.equals("Class Four")) {
            links3 = "4";
        } else if (links.equals("Class Five")) {
            links3 = "5";
        } else if (links.equals("Class Six")) {
            links3 = "6";
        } else if (links.equals("Class Seven")) {
            links3 = "7";
        } else if (links.equals("Class Eight")) {
            links3 = "8";
        } else if (links.equals("Class Nine")) {
            links3 = "9";
        } else if (links.equals("Class Ten")) {
            links3 = "10";
        } else if (links.equals("Class Eleven")) {
            links3 = "11";
        } else if (links.equals("Class Twelve")) {
            links3 = "12";
        }
        Toast.makeText(getApplicationContext(), "" + links3, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ShowOnlineClasses.class);
        i.putExtra("classnms", links3);
        i.putExtra("classsecs", links2);
        startActivity(i);
    }
}