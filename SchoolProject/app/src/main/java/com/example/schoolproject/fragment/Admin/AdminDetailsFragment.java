package com.example.schoolproject.fragment.Admin;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolproject.R;


public class AdminDetailsFragment extends Fragment implements View.OnClickListener {


    private CardView classCV,noticeCV,galleryCV,routineCV,sectionCV;

    public AdminDetailsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_admin_details, container, false);

        classCV = view.findViewById(R.id.adminAddClassesCV);
        noticeCV = view.findViewById(R.id.adminAddNoticeCV);
        galleryCV = view.findViewById(R.id.adminAddGalleryCV);
        routineCV = view.findViewById(R.id.adminAddRoutineCV);
        sectionCV = view.findViewById(R.id.adminAddSectionCV);

        classCV.setOnClickListener(this);
        noticeCV.setOnClickListener(this);
        galleryCV.setOnClickListener(this);
        routineCV.setOnClickListener(this);
        sectionCV.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.adminAddClassesCV){
            Navigation.findNavController(v).navigate(R.id.action_adminDetailsFragment_to_add_classesFragment);
        }else if(v.getId()==R.id.adminAddNoticeCV){
            Navigation.findNavController(v).navigate(R.id.action_adminDetailsFragment_to_addNoticeFragment);
        }else if(v.getId()==R.id.adminAddGalleryCV){
            Navigation.findNavController(v).navigate(R.id.action_adminDetailsFragment_to_addGalleryImageFragment);
        }else if(v.getId()==R.id.adminAddRoutineCV){
            Navigation.findNavController(v).navigate(R.id.action_adminDetailsFragment_to_addRoutineFragment);
        }else if(v.getId()==R.id.adminAddSectionCV){
            Navigation.findNavController(v).navigate(R.id.action_adminDetailsFragment_to_addSectionFragment);
        }
    }
}