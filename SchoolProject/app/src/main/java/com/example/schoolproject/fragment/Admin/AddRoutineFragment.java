package com.example.schoolproject.fragment.Admin;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schoolproject.R;
import com.example.schoolproject.model.RoutineUpdate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AddRoutineFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int IMAGE_REQUEST = 18;
    private Button choosebtn, savebtn, disbtn;
    private ImageView imageView;
    private DatabaseReference databaseReference, databaseReference2;
    private StorageReference storageReference;
    private Uri imageUri;
    private EditText routine_sec;
    private Spinner routine_spinner;
    private List RoutineClassNames;
    private String routneclsnm, routnesecnm, routneclsno;

    public AddRoutineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_routine, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("All_Classes Image");
        databaseReference2 = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference("All_Classes Image");

        routine_spinner = view.findViewById(R.id.add_routine_spinner);
        routine_sec = view.findViewById(R.id.add_routine_sec);
        choosebtn = view.findViewById(R.id.chooseRoutineImgID);
        imageView = view.findViewById(R.id.routineImgID);
        savebtn = view.findViewById(R.id.routineSaveImgID);
        // disbtn = view.findViewById(R.id.routineDisplayImgID);
        RoutineClassNames = new ArrayList<>();
        choosebtn.setOnClickListener(this);
        savebtn.setOnClickListener(this);
        //disbtn.setOnClickListener(this);

        routine_spinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onStart() {
        databaseReference2.child("Classes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RoutineClassNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String spinner = dataSnapshot.child("class_name").getValue(String.class);
                    RoutineClassNames.add(spinner);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, RoutineClassNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                routine_spinner.setAdapter(adapter);
                routine_spinner.setSelection(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.chooseRoutineImgID:

                openFileChooser();
                break;

            case R.id.routineSaveImgID:

                saveData();
                break;

        }

    }

    private void saveData() {

        routnesecnm = routine_sec.getText().toString().trim();
        String usr = routneclsno + routnesecnm;


        StorageReference ref =
                storageReference.child("" + usr + ".jpg");

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        ref.putBytes(byteArrayOutputStream.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                try {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while ((!uriTask.isSuccessful())) ;
                    Uri downloaduri = uriTask.getResult();
                    RoutineUpdate routineUpdate = new RoutineUpdate(routneclsnm, routnesecnm, downloaduri.toString());
                    databaseReference.child(usr).setValue(routineUpdate);
                    Toast.makeText(getContext(), "Routine Save successful", Toast.LENGTH_SHORT).show();
                    Log.e("empty", "onSuccess: empty");

                } catch (Exception e) {
                    Toast.makeText(getContext(), "aaaaa", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String getFileExtention(Uri uri) {

        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST &&
                resultCode == getActivity().RESULT_OK &&
                data != null && data.getData() != null) {

            imageUri = data.getData();
            Picasso.get().load(imageUri)
                    .into(imageView);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        routneclsnm = parent.getItemAtPosition(position).toString();
        if (routneclsnm.equals("Pre-Primary")) {
            routneclsno = "0";
        } else if (routneclsnm.equals("Class One")) {
            routneclsno = "1";
        } else if (routneclsnm.equals("Class Two")) {
            routneclsno = "2";
        } else if (routneclsnm.equals("Class Three")) {
            routneclsno = "3";
        } else if (routneclsnm.equals("Class Four")) {
            routneclsno = "4";
        } else if (routneclsnm.equals("Class Five")) {
            routneclsno = "5";
        } else if (routneclsnm.equals("Class Six")) {
            routneclsno = "6";
        } else if (routneclsnm.equals("Class Seven")) {
            routneclsno = "7";
        } else if (routneclsnm.equals("Class Eight")) {
            routneclsno = "8";
        } else if (routneclsnm.equals("Class Nine")) {
            routneclsno = "9";
        } else if (routneclsnm.equals("Class Ten")) {
            routneclsno = "10";
        } else if (routneclsnm.equals("Class Eleven")) {
            routneclsno = "11";
        } else if (routneclsnm.equals("Class Twelve")) {
            routneclsno = "12";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}