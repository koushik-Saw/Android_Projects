package com.example.schoolproject.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.schoolproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.widget.Toast.LENGTH_LONG;

public class RoutineInfoDetailsFragment extends Fragment {
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String a;
    TextView sec;
    ImageView routpic;
    public RoutineInfoDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_routine_info_details, container, false);
        storageReference = FirebaseStorage.getInstance().getReference("All_Classes Image");
        sec = view.findViewById(R.id.routclass);
        routpic = view.findViewById(R.id.routinepic);

        if (getArguments() != null) {
            Log.e("ff", "onCreateView: ");
            String b = getArguments().getString("class_name");
            String c = getArguments().getString("class_sec");
            String d =c;
            a = b + c;
            sec.setText(d);
        }

        return view;

    }

    @Override
    public void onStart() {
        StorageReference imgref = storageReference.child("" + a + ".jpg");
        imgref.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                routpic.setImageBitmap(bitmap);
            }
        });
        super.onStart();
    }
}