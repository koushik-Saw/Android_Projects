package com.example.collageproject.fragment;

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
import com.example.collageproject.adapters.SubjectAdapter;
import com.example.collageproject.models.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class RoutineSubjectFragment extends Fragment implements SubjectAdapter.onsubjectListener {
    DatabaseReference databaseReference;
    ArrayList<Courses> courseslist2;
    RecyclerView routinesubject_recyclerView;
    SubjectAdapter subjectAdapter;
    String a;

    public RoutineSubjectFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_routine_subject, container, false);
        if (getArguments() != null) {
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("fac_year");
            a = b;
            Toast.makeText(getContext(), a, LENGTH_LONG).show();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("subjects");
        courseslist2 = new ArrayList<>();
        routinesubject_recyclerView = view.findViewById(R.id.routinesubjects_recycleId);
        subjectAdapter = new SubjectAdapter(courseslist2,this);

        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseslist2.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        courseslist2.add(dataSnapshot1.getValue(Courses.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (subjectAdapter != null) {
                        subjectAdapter.getFilter().filter(a);
                        subjectAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                routinesubject_recyclerView.setAdapter(subjectAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }


    TextView g;
    String b;
    @Override
    public void onClickItem(int position, View view) {
        courseslist2.get(position);

        g = routinesubject_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.coursenm);
        b = g.getText().toString().trim();
        Bundle bundle = new Bundle();
        bundle.putString("sub_name", b);
        Navigation.findNavController(view).navigate(R.id.Show_routinefragment,bundle);

    }
}