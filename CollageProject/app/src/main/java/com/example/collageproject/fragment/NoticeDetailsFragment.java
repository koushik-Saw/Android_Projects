package com.example.collageproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.collageproject.R;


public class NoticeDetailsFragment extends Fragment {


    private TextView textView1,textView2;

    public NoticeDetailsFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice_details, container, false);

        textView1 = view.findViewById(R.id.noticeDetailsTitleTV);
        textView2 = view.findViewById(R.id.noticeDetailsMessageTV);

        if(getArguments()!=null){
            Log.e("ff", "onCreateView: ");

            String title = getArguments().getString("noticeTitle");
            String message = getArguments().getString("noticeMessage");

            textView1.setText(""+title);
            textView2.setText(""+message);
        }


        return view;
    }
}