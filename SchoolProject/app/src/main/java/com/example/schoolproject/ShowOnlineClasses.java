package com.example.schoolproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Adapters.ShowOnlineClassAdapters;
import com.example.schoolproject.model.OnlineClasses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowOnlineClasses extends AppCompatActivity implements ShowOnlineClassAdapters.OnOnlineclassListener {
    DatabaseReference databaseReference;
    RecyclerView online_recyclerView;
    ArrayList<OnlineClasses> onlineClasses2;
    ShowOnlineClassAdapters showOnlineClassAdapters;
    Toolbar toolbar;
    String g, h, j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_online_classes2);
        toolbar = findViewById(R.id.toolbartit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Online Classes");
        Intent i = getIntent();

        g = i.getStringExtra("classnms");
        h = i.getStringExtra("classsecs");
        j = g + h;

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Online Classes");
        onlineClasses2 = new ArrayList<>();
        online_recyclerView = findViewById(R.id.showclasses_recycleId);
        showOnlineClassAdapters = new ShowOnlineClassAdapters(onlineClasses2, this);
    }

    @Override
    protected void onStart() {
        Toast.makeText(getApplicationContext(), "" + j, Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlineClasses2.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        onlineClasses2.add(dataSnapshot1.getValue(OnlineClasses.class));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
                try {
                    if (showOnlineClassAdapters != null) {
                        showOnlineClassAdapters.getFilter().filter(j);
                        showOnlineClassAdapters.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                online_recyclerView.setAdapter(showOnlineClassAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }

    @Override
    public void onClickItem(int position) {
        onlineClasses2.get(position);

        TextView g = online_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.sample_online_class_links);
        String l = g.getText().toString().trim();

        if(l.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Link",Toast.LENGTH_LONG);
        }
        else {
            Uri uri = Uri.parse(l);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }
}