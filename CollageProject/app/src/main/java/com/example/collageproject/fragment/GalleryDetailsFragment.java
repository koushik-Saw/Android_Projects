package com.example.collageproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.collageproject.R;
import com.squareup.picasso.Picasso;


public class GalleryDetailsFragment extends Fragment {

    private ImageView galleryInfoImage;

    public GalleryDetailsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gallery_details, container, false);

        galleryInfoImage = view.findViewById(R.id.galleryInfoDetailsImage);
        String link = getArguments().getString("imageLink");


        Picasso.get()
                .load(link)
                .placeholder(R.drawable.lodding)
                .into(galleryInfoImage);


        return view;
    }
}