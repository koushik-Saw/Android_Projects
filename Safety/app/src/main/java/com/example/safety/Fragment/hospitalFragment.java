package com.example.safety.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safety.R;
import com.example.safety.adepter.HospitalAdepter;
import com.example.safety.model.Hospital;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class hospitalFragment extends Fragment implements HospitalAdepter.onCallListener {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    HospitalAdepter hospitalAdepter;
    ArrayList<Hospital> hospitals;
    Toolbar toolbar;
    SearchView searchView;
    TextView phn;
    String call;
    public hospitalFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("hospital");
        hospitals = new ArrayList<>();

        recyclerView = view.findViewById(R.id.hospital_recycleId);
        searchView = view.findViewById(R.id.searchhospitalId);
        toolbar = view.findViewById(R.id.hospital_toolbar);

        hospitalAdepter = new HospitalAdepter(hospitals, this);

        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hospitals.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        hospitals.add(dataSnapshot1.getValue(Hospital.class));
                    } catch (Exception e) {

                    }
                }
                recyclerView.setAdapter(hospitalAdepter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (hospitalAdepter != null) {
                        hospitalAdepter.getFilter().filter(query);

                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (hospitalAdepter != null) {
                        hospitalAdepter.getFilter().filter(newText);

                    }
                    return true;
                }
            });
        }
        super.onStart();
    }

    @Override
    public void onClickItem(int position) {
        hospitals.get(position);
        phn=recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.hospital_number);
        call=phn.getText().toString();
        Toast.makeText(getContext(),"n:"+call,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+call));
        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},201);
            return;
        }
        startActivity(intent);
    }
}