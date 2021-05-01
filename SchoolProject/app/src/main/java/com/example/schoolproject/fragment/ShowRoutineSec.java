package com.example.schoolproject.fragment;

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

import com.example.schoolproject.Adapters.ShowOnlineClassSecAdapter;
import com.example.schoolproject.Adapters.ShowRoutineSecAdapters;
import com.example.schoolproject.R;
import com.example.schoolproject.model.Sections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


public class ShowRoutineSec extends Fragment implements ShowRoutineSecAdapters.onRoutinesecListener {
    DatabaseReference databaseReference;
    RecyclerView routinesec_recyclerView;
    ArrayList<Sections> routineClasses;
    ShowRoutineSecAdapters showRoutineSecAdapters;
    String a;
    public ShowRoutineSec() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_routine_sec, container, false);
        if(getArguments()!=null){
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("class_name");

            a = b;
            Toast.makeText(getContext(),a,LENGTH_LONG).show();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Sections");
        routinesec_recyclerView = view.findViewById(R.id.showroutinesec_recycleId);
        routineClasses = new ArrayList<Sections>();
        showRoutineSecAdapters = new ShowRoutineSecAdapters(routineClasses, this);

        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                routineClasses.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        routineClasses.add(dataSnapshot1.getValue(Sections.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (showRoutineSecAdapters != null) {
                        showRoutineSecAdapters.getFilter().filter(a);
                        showRoutineSecAdapters.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                routinesec_recyclerView.setAdapter(showRoutineSecAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
    TextView classnm, classsc;
    String links, links2, links3;
    @Override
    public void onClickItem(int position,View view) {
        routineClasses.get(position);

        classnm = routinesec_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.sample_routine_cnlas_sec_names);
        classsc = routinesec_recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.sample_routine_classSec);

        links = classnm.getText().toString().trim();
        links2 = classsc.getText().toString().trim();
        if (links.equals("Pre-Primary")) {
            links3 = "0";
        } else if (links.equals("Class One")) {
            links3 = "1";
        } else if (links.equals("Class Two")) {
            links3 = "2";
        } else if (links.equals("Class Three")) {
            links3 = "3";
        } else if (links.equals("Class Four")) {
            links3 = "4";
        } else if (links.equals("Class Five")) {
            links3 = "5";
        } else if (links.equals("Class Six")) {
            links3 = "6";
        } else if (links.equals("Class Seven")) {
            links3 = "7";
        } else if (links.equals("Class Eight")) {
            links3 = "8";
        } else if (links.equals("Class Nine")) {
            links3 = "9";
        } else if (links.equals("Class Ten")) {
            links3 = "10";
        }else if (links.equals("Class Eleven")) {
            links3 = "11";
        } else if (links.equals("Class Twelve")) {
            links3 = "12";
        }

        Bundle bundle = new Bundle();
        bundle.putString("class_name", links3);
        bundle.putString("class_sec",links2);

        Navigation.findNavController(view).navigate(R.id.action_routinesecFragment_to_routineInfoDetailsFragment,bundle);
    }
}