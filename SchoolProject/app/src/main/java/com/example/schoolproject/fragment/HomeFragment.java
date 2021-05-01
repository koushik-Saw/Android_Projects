package com.example.schoolproject.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.schoolproject.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    LinearLayout l1,l2,l3;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        l1 = view.findViewById(R.id.home_routine);
        l2 = view.findViewById(R.id.home_notice);
        l3 = view.findViewById(R.id.home_online_classes);
        imageSlider =  view.findViewById(R.id.image_slider);
        final List<SlideModel> imageList  = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.image_slider , ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.slider3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList , ScaleTypes.FIT);// for one image
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
                Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_onlineFragment);
            }
        });
        Toast.makeText(getContext(), "Home", Toast.LENGTH_SHORT).show();
        return view;
    }
}