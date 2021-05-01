package com.example.schoolproject.fragment.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schoolproject.R;
import com.example.schoolproject.model.AddSections;
import com.example.schoolproject.model.Sections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AddSectionFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText Section_sec;
    Button addsec;
    DatabaseReference databaseReference, databaseReference2;
    List addSectionclassnames;
    private Spinner addSectionspinner;
    String Sectionclassno, Sectionclassname, SectionclassSec;

    public AddSectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_section, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Sections");

        Section_sec = view.findViewById(R.id.addSection_section);
        addSectionspinner = view.findViewById(R.id.addSectionSpinner);
        addsec = view.findViewById(R.id.addSectionbtn);
        addSectionclassnames = new ArrayList<>();

        addSectionspinner.setOnItemSelectedListener(this);
        addsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (Sectionclassname.equals("Pre-Primary")) {
                            Sectionclassno = "0";
                        } else if (Sectionclassname.equals("Class One")) {
                            Sectionclassno = "1";
                        } else if (Sectionclassname.equals("Class Two")) {
                            Sectionclassno = "2";
                        } else if (Sectionclassname.equals("Class Three")) {
                            Sectionclassno = "3";
                        } else if (Sectionclassname.equals("Class Four")) {
                            Sectionclassno = "4";
                        } else if (Sectionclassname.equals("Class Five")) {
                            Sectionclassno = "5";
                        } else if (Sectionclassname.equals("Class Six")) {
                            Sectionclassno = "6";
                        } else if (Sectionclassname.equals("Class Seven")) {
                            Sectionclassno = "7";
                        } else if (Sectionclassname.equals("Class Eight")) {
                            Sectionclassno = "8";
                        } else if (Sectionclassname.equals("Class Nine")) {
                            Sectionclassno = "9";
                        } else if (Sectionclassname.equals("Class Ten")) {
                            Sectionclassno = "10";
                        } else if (Sectionclassname.equals("Class Eleven")) {
                            Sectionclassno = "11";
                        } else if (Sectionclassname.equals("Class Twelve")) {
                            Sectionclassno = "12";
                        }

                        SectionclassSec = Section_sec.getText().toString().trim();

                        if (SectionclassSec.isEmpty()) {
                            Section_sec.setError("Enter a valid Section");
                        } else {
                            String key = Sectionclassno + SectionclassSec;
                            AddSections sections = new AddSections(Sectionclassname, SectionclassSec);
                            databaseReference2.child(key).setValue(sections);
                            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        databaseReference.child("Classes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                addSectionclassnames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String spinner = dataSnapshot.child("class_name").getValue(String.class);
                    addSectionclassnames.add(spinner);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, addSectionclassnames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addSectionspinner.setAdapter(adapter);
                addSectionspinner.setSelection(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Sectionclassname = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}