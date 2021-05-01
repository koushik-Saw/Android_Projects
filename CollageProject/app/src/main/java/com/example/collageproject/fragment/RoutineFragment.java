package com.example.collageproject.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.collageproject.R;


public class RoutineFragment extends Fragment implements View.OnClickListener {

    private Button hsc_gen, honours,degree;

    public RoutineFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine, container, false);

        hsc_gen = view.findViewById(R.id.routine_hsc_general);
        honours = view.findViewById(R.id.routine_honours);
        degree = view.findViewById(R.id.routine_degree);

        degree.setOnClickListener(this);
        hsc_gen.setOnClickListener(this);
        honours.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.routine_hsc_general)
        {
            String b = "HSC";
            Bundle bundle = new Bundle();
            bundle.putString("course_name", b);
            Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.routinedeptsFregment,bundle);
        }
        else if (v.getId()==R.id.routine_honours){
            String b = "Honours";
            Bundle bundle = new Bundle();
            bundle.putString("course_name", b);
            Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.routinedeptsFregment,bundle);
        }
        else if (v.getId()==R.id.routine_degree){
            String b = "Degree";
            Bundle bundle = new Bundle();
            bundle.putString("course_name", b);
            Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.routinedeptsFregment,bundle);
        }
    }
}