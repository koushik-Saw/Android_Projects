package com.example.collageproject.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collageproject.R;
import com.example.collageproject.adapters.NoticeAdepter;
import com.example.collageproject.adapters.ShowOnlineClassesAdapter;
import com.example.collageproject.models.Courses;
import com.example.collageproject.models.Notice;
import com.example.collageproject.models.OnlineClasses;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShowOnlineClasses extends Fragment implements ShowOnlineClassesAdapter.OnopenListener {

    DatabaseReference databaseReference;
    ArrayList<OnlineClasses> onlineClasses;
    RecyclerView online_recylerview;
    ShowOnlineClassesAdapter showOnlineClassesAdapter;
    String a;
    TextView sec;
    public ShowOnlineClasses() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_online_classes, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Online Classes");
        onlineClasses = new ArrayList<OnlineClasses>();
        online_recylerview = view.findViewById(R.id.onlineShow_recycleId);
        sec = view.findViewById(R.id.onclass);
        showOnlineClassesAdapter = new ShowOnlineClassesAdapter(onlineClasses,this);
        if (getArguments() != null) {
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("sub_name");
            a = b ;
            sec.setText(a);
        }
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onlineClasses.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        onlineClasses.add(dataSnapshot1.getValue(OnlineClasses.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (showOnlineClassesAdapter != null) {
                        showOnlineClassesAdapter.getFilter().filter(a);
                        showOnlineClassesAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                online_recylerview.setAdapter(showOnlineClassesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onClickItem(int position, View view) {
        onlineClasses.get(position);

        TextView g = online_recylerview.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.bookLink);
        String l = g.getText().toString().trim();

        if(l.isEmpty()){
            Toast.makeText(getContext(),"No Link",Toast.LENGTH_LONG);
        }
        else {
            Uri uri = Uri.parse(l);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}