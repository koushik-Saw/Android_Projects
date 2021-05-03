package com.example.collageproject.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.collageproject.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private ImageSlider imageSlider;
    private LinearLayout l1,l2,l3,l4;
    private ImageView galleryIV;
    private Button btnRoutine,btnNotice,btnClasses;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        l1 = view.findViewById(R.id.home_routine);
        l2 = view.findViewById(R.id.home_notice);
        l3 = view.findViewById(R.id.home_online_classes);
        l4 = view.findViewById(R.id.home_mujib);
        imageSlider =  view.findViewById(R.id.image_slider);
        galleryIV =  view.findViewById(R.id.galleryImgIV);
        btnRoutine =  view.findViewById(R.id.routineBtn);
        btnNotice =  view.findViewById(R.id.noticeBtn);
        btnClasses =  view.findViewById(R.id.classesBtn);

        final List<SlideModel> imageList  = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.image_slider , ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider2,  ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider3,  ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList ,ScaleTypes.FIT);// for one image
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_routineFragment);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_noticeFragment);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_onlineClassesFragment);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_mujibDetailsFragment);

            }
        });
        galleryIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_galleryFragment);

            }
        });
        btnRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_routineFragment);

            }
        });
        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_noticeFragment);

            }
        });
        btnClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_onlineClassesFragment);

            }
        });

        return view;
    }
}