package com.example.nctb_books.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nctb_books.Adapters.ClassAdapters;
import com.example.nctb_books.Model.Classes;
import com.example.nctb_books.R;
import com.example.nctb_books.ShowBooks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class TwoFragment extends Fragment implements ClassAdapters.onClasslinstener {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ClassAdapters routineAdapters;
    ArrayList<Classes> routines;
    String c;
    TextView d;
    ViewGroup container;

    public TwoFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FourFragment fourFragment = new FourFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);
        this.container = container;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Classes");
        routines = new ArrayList<Classes>();

        recyclerView = view.findViewById(R.id.routine_recycleId);
        routineAdapters = new ClassAdapters(routines, this);

        return view;
    }

    @Override
    public void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                routines.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        routines.add(dataSnapshot1.getValue(Classes.class));
                    } catch (Exception e) {

                    }
                }
                recyclerView.setAdapter(routineAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }

    @Override
    public void onClickItem(int position) {
        routines.get(position);
        d = recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.Routineclassno);
        c = d.getText().toString().trim();
        if (c.equals("Class One")) {
            c = "01";
        } else if (c.equals("Class Two")) {
            c = "02";
        } else if (c.equals("Class Three")) {
            c = "03";
        } else if (c.equals("Class Four")) {
            c = "04";
        } else if (c.equals("Class Five")) {
            c = "05";
        } else if (c.equals("Class Six")) {
            c = "06";
        } else if (c.equals("Class Seven")) {
            c = "07";
        } else if (c.equals("Class Eight")) {
            c = "08";
        } else if (c.equals("Class Nine")) {
            c = "09";
        } else if (c.equals("Class Nine & Ten")) {
            c = "10";
        }

        Intent i = new Intent(getContext(), ShowBooks.class);
        i.putExtra("key", c);
        startActivity(i);
    }
}