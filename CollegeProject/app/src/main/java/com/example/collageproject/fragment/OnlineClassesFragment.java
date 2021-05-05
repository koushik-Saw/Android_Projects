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


public class OnlineClassesFragment extends Fragment implements View.OnClickListener{

    private Button hsc_gen, honours,degree;

    public OnlineClassesFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_online_classes, container, false);

        hsc_gen = view.findViewById(R.id.online_hsc_general);
        honours = view.findViewById(R.id.online_honours );
        degree = view.findViewById(R.id.online_degree);

        degree.setOnClickListener(this);
        hsc_gen.setOnClickListener(this);
        honours.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.online_hsc_general)
        {
            String b = "HSC";
            Bundle bundle = new Bundle();
            bundle.putString("course_name", b);
            Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.onlineClassesDeptfragment,bundle);
        }
        else if (v.getId()==R.id.online_honours){
            String b = "Honours";
            Bundle bundle = new Bundle();
            bundle.putString("course_name", b);
            Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.onlineClassesDeptfragment,bundle);
        }
        else if (v.getId()==R.id.online_degree){
            String b = "Degree";
            Bundle bundle = new Bundle();
            bundle.putString("course_name", b);
            Navigation.findNavController((Activity) getContext(),R.id.nav_host_fragment).navigate(R.id.onlineClassesDeptfragment,bundle);
        }
    }
}