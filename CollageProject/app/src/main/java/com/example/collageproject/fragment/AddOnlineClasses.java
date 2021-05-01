package com.example.collageproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.collageproject.R;
import com.example.collageproject.models.OnlineClasses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AddOnlineClasses extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner add_online_Spinner;
    Button addclass;
    EditText sub,link;
    DatabaseReference databaseReference,databaseReference2;
    String classname;
    private List OnlineClassNames;
    public AddOnlineClasses() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_online_classes, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Online Classes");
        add_online_Spinner =view.findViewById(R.id.add_onlineClass_spinner);
        addclass = view.findViewById(R.id.addonlineClassbtn);
        sub = view.findViewById(R.id.add_onlineClass_Subject);
        link = view.findViewById(R.id.add_onlineClaass_Link);
        OnlineClassNames = new ArrayList<>();
        add_online_Spinner.setOnItemSelectedListener(this);
        addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subs = sub.getText().toString().toLowerCase().trim();
                String fac_sub = classname+subs;
                String links = link.getText().toString().trim();
                String faculty = classname;
                OnlineClasses onlineClasses = new OnlineClasses(fac_sub,subs,faculty,links);
                databaseReference2.child(fac_sub).setValue(onlineClasses);
                Toast.makeText(getContext(),fac_sub,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        Query q = databaseReference.child("subjects").orderByChild("subjects");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OnlineClassNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String spinner = dataSnapshot.child("subjects").getValue(String.class);
                    OnlineClassNames.add(spinner);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, OnlineClassNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                add_online_Spinner.setAdapter(adapter);
                add_online_Spinner.setSelection(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        classname = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}