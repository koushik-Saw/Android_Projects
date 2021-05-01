package com.example.schoolproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Adapters.RoutineAdapters;
import com.example.schoolproject.R;
import com.example.schoolproject.ShowOnlineClassesSecs;
import com.example.schoolproject.model.All_Classes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RoutineFragment extends Fragment implements RoutineAdapters.onClasslinstener {

    DatabaseReference databaseReference;
    RecyclerView routinerecyclerView;
    RoutineAdapters routineAdapters;
    ArrayList<All_Classes> allClasses;
    String b;
    public RoutineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_routine, container, false);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Classes");
        allClasses = new ArrayList<All_Classes>();

        routinerecyclerView = view.findViewById(R.id.routine_recycleId);
        routineAdapters = new RoutineAdapters(allClasses, this);

        return view;
    }

    @Override
    public void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allClasses.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        allClasses.add(dataSnapshot1.getValue(All_Classes.class));
                    } catch (Exception e) {

                    }
                }
                routinerecyclerView.setAdapter(routineAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }
    TextView g;
    @Override
    public void onClickItem(int position,View view) {
        allClasses.get(position);

        g = routinerecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.Routineclassno);
        b = g.getText().toString().trim();
        Log.e("classnm", "onClickItem: "+b);
        Bundle bundle = new Bundle();
        bundle.putString("class_name", b);
        Navigation.findNavController(view).navigate(R.id.action_routineFragment_to_routinesecFragment,bundle);
    }
}