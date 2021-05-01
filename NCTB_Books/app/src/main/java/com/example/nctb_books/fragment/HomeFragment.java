package com.example.nctb_books.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nctb_books.R;


public class HomeFragment extends Fragment {

    Button all,onetoten,math,learn;


    public HomeFragment() {

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

       // all = view.findViewById(R.id.all_books);
        onetoten = view.findViewById(R.id.onetoten);
       // math = view.findViewById(R.id.math_solution);
        learn = view.findViewById(R.id.learn_new);

        onetoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_twoFragment);
            }
        });
        /*all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_oneFragment);
            }
        });*/
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_fourFragment);
            }
        });
        return view;
    }
}