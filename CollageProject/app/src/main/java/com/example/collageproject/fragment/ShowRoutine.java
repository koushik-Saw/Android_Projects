package com.example.collageproject.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.collageproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class ShowRoutine extends Fragment {

    StorageReference storageReference;
    String a;
    TextView sec;
    ImageView routpic;

    public ShowRoutine() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_routine, container, false);
        storageReference = FirebaseStorage.getInstance().getReference("Routine Image");
        sec = view.findViewById(R.id.routclass);
        routpic = view.findViewById(R.id.routinepic);
        if (getArguments() != null) {
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("sub_name");
            a = b ;
            sec.setText(a);
        }

        return view;

    }

    @Override
    public void onStart() {
        StorageReference imgref = storageReference.child("" + a + ".jpg");
        imgref.getBytes(4096*3910).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                routpic.setImageBitmap(bitmap);
            }
        });
        super.onStart();
    }
}