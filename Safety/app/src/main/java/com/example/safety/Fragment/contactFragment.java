package com.example.safety.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safety.R;


public class contactFragment extends Fragment {


    private EditText nameedit, feedbackedit;
    private Button send, clear;
    private Toolbar toolbar;

    public contactFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        send = view.findViewById(R.id.send);
        clear =  view.findViewById(R.id.clearid);
        nameedit =view.findViewById(R.id.enameid);
        feedbackedit = view.findViewById(R.id.efeedbackid);
        toolbar =  (Toolbar) view.findViewById(R.id.feedtoolbarId);
        toolbar.setTitle("Contact Us");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                Toast.makeText(getContext(), "sdfsd", Toast.LENGTH_SHORT).show();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameedit.setText("");
                feedbackedit.setText("");

            }
        });




        return view;
    }

    private void sendEmail() {

        String[] TO = {"saikot15-7484@diu.edu.bd"};
        String[] CC = {""};
        String name = nameedit.getText().toString();
        String message = feedbackedit.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        //emailIntent.setData(Uri.parse("mailto:saikot15-7484@diu.edu.bd"));
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
       //emailIntent.putExtra(Intent.EXTRA_TEXT, "Name:" + name + "\n Message:" + message);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: "+name + "\n Message: "+message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            nameedit.setText("");
            feedbackedit.setText("");

            Toast.makeText(getContext(), "send Successful!", Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }




    }
