package com.example.nctb_books.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nctb_books.Model.ClassBookList;
import com.example.nctb_books.Model.Classes;
import com.example.nctb_books.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ThreeFragment extends Fragment {

    EditText additm,addno;
    Button addbt;
    DatabaseReference databaseReference;

    public ThreeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_three, container, false);

        additm = view.findViewById(R.id.additems);
        addno = view.findViewById(R.id.addno);
        addbt = view.findViewById(R.id.addbtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("Solutions");

        addbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String items = additm.getText().toString().trim();
                String no = addno.getText().toString().trim();
                String key = no;
                ClassBookList classBookList = new ClassBookList(no,items);
                databaseReference.child(key).setValue(classBookList);
                Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}