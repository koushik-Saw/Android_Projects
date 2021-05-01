package com.example.schoolproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.schoolproject.Adapters.GalleryAdepter;
import com.example.schoolproject.R;
import com.example.schoolproject.model.GalleryImageUpload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment implements GalleryAdepter.onGalleryListener{

    private RecyclerView galleryRecyclerView;
    private GalleryAdepter galleryAdepter;
    private DatabaseReference databaseReference;
    private List<GalleryImageUpload> galleryImageUploadList;

    public GalleryFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Gallery Image");


        galleryRecyclerView = view.findViewById(R.id.galleryRecycleView);
        galleryRecyclerView.setHasFixedSize(true);
        galleryImageUploadList = new ArrayList<>();

        //  LinearLayoutManager llm = new LinearLayoutManager(getContext());
        GridLayoutManager glm = new GridLayoutManager(getContext(),3);
        galleryRecyclerView.setLayoutManager(glm);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()){

                    GalleryImageUpload upload = snapshot1.getValue(GalleryImageUpload.class);
                    galleryImageUploadList.add(upload);
                }

                galleryAdepter = new GalleryAdepter(getContext(),galleryImageUploadList,GalleryFragment.this);
                galleryRecyclerView.setAdapter(galleryAdepter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

    @Override
    public void onClickItem(int position, View v) {

        Bundle bundle = new Bundle();
        bundle.putString("imageLink",galleryImageUploadList.get(position).getGalleryImageUri());
        Navigation.findNavController(v).navigate(R.id.action_galleryFragment_to_galleryInfoDetailsFragment,bundle);

    }
}