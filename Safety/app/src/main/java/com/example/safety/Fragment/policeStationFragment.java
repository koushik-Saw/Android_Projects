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
import com.example.safety.adepter.PoliceStationAdepter;
import com.example.safety.model.PoliceStation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class policeStationFragment extends Fragment implements PoliceStationAdepter.onCallListener {

    DatabaseReference databaseReference;
    RecyclerView recyclerView1;
    PoliceStationAdepter policeStationAdepter;
    ArrayList<PoliceStation> policeStations;
    Toolbar toolbar;
    SearchView searchView;
    TextView phn;
    String call;

    public policeStationFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_police_station, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("police station");
        policeStations =new ArrayList<PoliceStation>();

        recyclerView1 = view.findViewById(R.id.police_recycleId);
        searchView = view.findViewById(R.id.searchpoliceId);
        toolbar = view.findViewById(R.id.police_toolbar);
        policeStationAdepter = new PoliceStationAdepter(policeStations, this);
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                policeStations.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        policeStations.add(dataSnapshot1.getValue(PoliceStation.class));
                    } catch (Exception e) {

                    }
                }
                recyclerView1.setAdapter(policeStationAdepter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (policeStationAdepter != null) {
                        policeStationAdepter.getFilter().filter(query);

                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (policeStationAdepter != null) {
                        policeStationAdepter.getFilter().filter(newText);

                    }
                    return true;
                }
            });
        }
        super.onStart();
    }

    @Override
    public void onClickItem(int position) {
        policeStations.get(position);
        phn=recyclerView1.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.police_station_number);
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