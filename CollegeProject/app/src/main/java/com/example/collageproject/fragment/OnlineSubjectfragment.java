package com.example.collageproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collageproject.R;
import com.example.collageproject.adapters.SubjectAdapter;
import com.example.collageproject.models.Courses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import static android.widget.Toast.LENGTH_LONG;


public class OnlineSubjectfragment extends Fragment implements SubjectAdapter.onsubjectListener {

    DatabaseReference databaseReference;
    ArrayList<Courses> onlinrcourseslist2;
    RecyclerView onlinesubject_recyclerView;
    SubjectAdapter onlinesubjectAdapter;
    String a;


    public OnlineSubjectfragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_online_subjectfragment, container, false);
        if (getArguments() != null) {
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("fac_year");
            a = b;
            Toast.makeText(getContext(), a, LENGTH_LONG).show();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("subjects");
        onlinrcourseslist2 = new ArrayList<>();
        onlinesubject_recyclerView = view.findViewById(R.id.onlinesubjects_recycleId);
        onlinesubjectAdapter = new SubjectAdapter(onlinrcourseslist2,this);

        return view;

    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlinrcourseslist2.clear();
                onlinesubjectAdapter.notifyDataSetChanged();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        onlinrcourseslist2.add(dataSnapshot1.getValue(Courses.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (onlinesubjectAdapter != null) {
                        onlinesubjectAdapter.getFilter().filter(a);
                        onlinesubjectAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                try {

                    onlinesubject_recyclerView.setAdapter(onlinesubjectAdapter);
                }catch (Exception eg){
                    Toast.makeText(getContext(),""+eg,LENGTH_LONG).show();
                }
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
        onlinrcourseslist2.get(position);

        g = Objects.requireNonNull(onlinesubject_recyclerView.findViewHolderForAdapterPosition(position)).itemView.findViewById(R.id.coursenm);
        b = g.getText().toString().trim();
        Bundle bundle = new Bundle();
        bundle.putString("sub_name", b);
        Navigation.findNavController(view).navigate(R.id.onlineClassesShowfragment,bundle);
    }
}