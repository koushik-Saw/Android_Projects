package com.example.collageproject;

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

import com.example.collageproject.models.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AddNoticeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText ntctitle, ntcbdy, dte;
    Button addntc;
    String classnos, title, body, date;
    DatabaseReference databaseReference, databaseReference2;
    List addnoticeclassnames;
    private Spinner addnoticespinner;

    public AddNoticeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_notice, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Notice");


        ntctitle = view.findViewById(R.id.addnotice_title);
        ntcbdy = view.findViewById(R.id.addnotice_body);
        dte = view.findViewById(R.id.addnotice_dates);
        addnoticeclassnames = new ArrayList<>();
        addntc = view.findViewById(R.id.addnoticesbtn);
        addnoticespinner = view.findViewById(R.id.addnoticeSpinner);
        addnoticespinner.setOnItemSelectedListener(this);
        addntc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = ntctitle.getText().toString();
                body = ntcbdy.getText().toString();
                date = dte.getText().toString();
                String key = databaseReference.push().getKey();
                Notice notice = new Notice(classnos, date, title, body);
                databaseReference2.child(key).setValue(notice);
                Toast.makeText(getContext(), "New Notice Added", Toast.LENGTH_LONG).show();
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
                addnoticeclassnames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String spinner = dataSnapshot.child("subjects").getValue(String.class);
                    addnoticeclassnames.add(spinner);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, addnoticeclassnames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addnoticespinner.setAdapter(adapter);
                addnoticespinner.setSelection(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        classnos = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}