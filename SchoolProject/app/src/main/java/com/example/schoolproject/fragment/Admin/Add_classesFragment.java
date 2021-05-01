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
import com.example.schoolproject.model.OnlineClasses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Add_classesFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText clslnk, sec, sub;
    Button addclaslnk;
    String onlineclassno, onlineclassname, onlineclasssub, onlineclasssec, onlineclasslink;
    DatabaseReference databaseReference, databaseReference2;
    List addonlineclassnames;
    private Spinner online_spinner;


    public Add_classesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_classes, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Online Classes");

        clslnk = view.findViewById(R.id.add_online_class_link);
        sec = view.findViewById(R.id.add_online_classes_sec);
        sub = view.findViewById(R.id.add_online_class_sub);
        addclaslnk = view.findViewById(R.id.add_online_class_link_btnd);
        online_spinner = view.findViewById(R.id.add_online_classes_spinner);

        addonlineclassnames = new ArrayList<>();

        online_spinner.setOnItemSelectedListener(this);

        addclaslnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (onlineclassname.equals("Pre-Primary")) {
                            onlineclassno = "0";
                        } else if (onlineclassname.equals("Class One")) {
                            onlineclassno = "1";
                        } else if (onlineclassname.equals("Class Two")) {
                            onlineclassno = "2";
                        } else if (onlineclassname.equals("Class Three")) {
                            onlineclassno = "3";
                        } else if (onlineclassname.equals("Class Four")) {
                            onlineclassno = "4";
                        } else if (onlineclassname.equals("Class Five")) {
                            onlineclassno = "5";
                        } else if (onlineclassname.equals("Class Six")) {
                            onlineclassno = "6";
                        } else if (onlineclassname.equals("Class Seven")) {
                            onlineclassno = "7";
                        } else if (onlineclassname.equals("Class Eight")) {
                            onlineclassno = "8";
                        } else if (onlineclassname.equals("Class Nine")) {
                            onlineclassno = "9";
                        } else if (onlineclassname.equals("Class Ten")) {
                            onlineclassno = "10";
                        }else if (onlineclassname.equals("Class Eleven")) {
                            onlineclassno = "11";
                        }
                        else if (onlineclassname.equals("Class Twelve")) {
                            onlineclassno = "12";
                        }

                        onlineclasssub = sub.getText().toString().trim();
                        onlineclasssec = sec.getText().toString().trim();
                        onlineclasslink = clslnk.getText().toString().trim();


                        if (onlineclasssec.isEmpty()) {
                            sec.setError("Enter a valid Section");
                        } else {
                            String key = onlineclassno + onlineclasssec + onlineclasssub;
                            OnlineClasses onlineClasses = new OnlineClasses(key, onlineclasslink,onlineclasssub);
                            databaseReference2.child(key).setValue(onlineClasses);
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
                addonlineclassnames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String spinner = dataSnapshot.child("class_name").getValue(String.class);
                    addonlineclassnames.add(spinner);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, addonlineclassnames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                online_spinner.setAdapter(adapter);
                online_spinner.setSelection(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onlineclassname = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}