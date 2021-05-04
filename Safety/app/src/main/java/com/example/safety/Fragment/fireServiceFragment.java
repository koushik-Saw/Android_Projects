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
import com.example.safety.adepter.FireServiceAdepter;
import com.example.safety.model.FireService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fireServiceFragment extends Fragment implements FireServiceAdepter.onCallListener {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    FireServiceAdepter fireServiceAdepter;
    ArrayList<FireService> fireServices;
    Toolbar toolbar;
    SearchView searchView;
    TextView phn;
    String call;

    public fireServiceFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fire_service, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("fire service");
        fireServices = new ArrayList<FireService>();

        recyclerView = view.findViewById(R.id.fire_recycleId);
        searchView = view.findViewById(R.id.searchfireaddress);
        toolbar = view.findViewById(R.id.firetoolbar);
        fireServiceAdepter = new FireServiceAdepter(fireServices, this);
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fireServices.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        fireServices.add(dataSnapshot1.getValue(FireService.class));
                    } catch (Exception e) {

                    }
                }
                recyclerView.setAdapter(fireServiceAdepter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (fireServiceAdepter != null) {
                        fireServiceAdepter.getFilter().filter(query);

                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (fireServiceAdepter != null) {
                        fireServiceAdepter.getFilter().filter(newText);

                    }
                    return true;
                }
            });
        }
        super.onStart();
    }

    @Override
    public void onClickItem(int position) {
        fireServices.get(position);
        phn=recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.fire_number);
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