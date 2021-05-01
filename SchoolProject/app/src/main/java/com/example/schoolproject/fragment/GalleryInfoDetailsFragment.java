package com.example.schoolproject.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.schoolproject.R;
import com.squareup.picasso.Picasso;


public class GalleryInfoDetailsFragment extends Fragment {

    private ImageView galleryInfoImage;

    public GalleryInfoDetailsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_info_details, container, false);
        galleryInfoImage = view.findViewById(R.id.galleryInfoDetailsImage);
        String link = getArguments().getString("imageLink");


        Picasso.get()
                .load(link)
                .placeholder(R.drawable.lodding)
                .into(galleryInfoImage);



        return view;
    }




}