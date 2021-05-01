package com.example.collageproject.fragment;

import android.app.Activity;
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


public class RoutinedeptsFragment extends Fragment implements Courseadapter.onRoutineCourseListener {
    DatabaseReference databaseReference;
    ArrayList<Courses> courseslist;
    RecyclerView routinedepts_recyclerView;
    Courseadapter courseadapter;
    String a;

    public RoutinedeptsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_routinedepts, container, false);
        if(getArguments()!=null){
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("course_name");

            a = b;
            Toast.makeText(getContext(),a,LENGTH_LONG).show();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses");
        courseslist = new ArrayList<>();
        routinedepts_recyclerView = view.findViewById(R.id.routinedepts_recycleId);
        courseadapter = new Courseadapter(courseslist,this);
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseslist.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        courseslist.add(dataSnapshot1.getValue(Courses.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (courseadapter != null) {
                        courseadapter.getFilter().filter(a);
                        courseadapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                routinedepts_recyclerView.setAdapter(courseadapter);
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
        courseslist.get(position);

        g = routinedepts_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.coursenm);

        String b = g.getText().toString().trim();
        String c;

        Bundle bundle = new Bundle();
        bundle.putString("faculty_name", b);
        Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.routineDetailsFragment,bundle);

    }
}