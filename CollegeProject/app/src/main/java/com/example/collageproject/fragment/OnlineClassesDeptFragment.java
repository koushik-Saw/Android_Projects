package com.example.collageproject.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collageproject.R;
import com.example.collageproject.adapters.Courseadapter;
import com.example.collageproject.models.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class OnlineClassesDeptFragment extends Fragment implements Courseadapter.onRoutineCourseListener {

    DatabaseReference databaseReference;
    ArrayList<Courses> onlinecourseslist;
    RecyclerView onlinedepts_recyclerView;
    Courseadapter onlinecourseadapter;
    String a;

    public OnlineClassesDeptFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_online_classes_dept, container, false);
        if(getArguments()!=null){
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("course_name");

            a = b;
            Toast.makeText(getContext(),a,LENGTH_LONG).show();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses");
        onlinecourseslist = new ArrayList<>();
        onlinedepts_recyclerView = view.findViewById(R.id.onlineclassesdepts_recycleId);
        onlinecourseadapter = new Courseadapter(onlinecourseslist,this);
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlinecourseslist.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        onlinecourseslist.add(dataSnapshot1.getValue(Courses.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (onlinecourseadapter != null) {
                        onlinecourseadapter.getFilter().filter(a);
                        onlinecourseadapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                onlinedepts_recyclerView.setAdapter(onlinecourseadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
    TextView g;
    @Override
    public void onClickItem(int position, View view) {
        onlinecourseslist.get(position);

        g = onlinedepts_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.coursenm);

        String b = g.getText().toString().trim();
        String c;

        Bundle bundle = new Bundle();
        bundle.putString("faculty_name", b);
        Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.onlineClassesFacultyfragment,bundle);
    }
}