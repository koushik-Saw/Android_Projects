package com.example.schoolproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.Adapters.OnlineClassAdapter;
import com.example.schoolproject.R;
import com.example.schoolproject.ShowOnlineClassesSecs;
import com.example.schoolproject.model.All_Classes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OnlineClassesFragment extends Fragment implements OnlineClassAdapter.OnClassclick {

DatabaseReference databaseReference;
ArrayList<All_Classes> all_classes;
OnlineClassAdapter onlineClassAdapter;
RecyclerView onlineclassrecyclerView;
String b;
    public OnlineClassesFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_onlineclasees, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Classes");
        onlineclassrecyclerView = view.findViewById(R.id.online_recycleId);
        all_classes = new ArrayList<>();
        onlineClassAdapter = new OnlineClassAdapter(all_classes,this);

        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                all_classes.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        all_classes.add(dataSnapshot1.getValue(All_Classes.class));
                    } catch (Exception e) {

                    }
                }
                onlineclassrecyclerView.setAdapter(onlineClassAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
TextView g;
    @Override
    public void onClickItem(int position) {
        all_classes.get(position);

        g = onlineclassrecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.onlineclassno);
        b = g.getText().toString().trim();

        Intent i = new Intent(getContext(), ShowOnlineClassesSecs.class);
        i.putExtra("Class_Name", b);
        startActivity(i);
    }
}