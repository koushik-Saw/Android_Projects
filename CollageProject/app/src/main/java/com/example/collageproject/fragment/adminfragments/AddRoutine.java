package com.example.collageproject.fragment.adminfragments;

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

import com.example.collageproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddRoutine extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int IMAGE_REQUEST = 18;
    private Button choosebtn, savebtn, disbtn;
    private ImageView imageView;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Uri imageUri;
    private EditText routine_sub;
    private Spinner routine_spinner;
    private List RoutineClassNames;
    String routneclsnm;

    public AddRoutine() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_routine, container, false);

        storageReference = FirebaseStorage.getInstance().getReference("Routine Image");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        routine_spinner = view.findViewById(R.id.add_routine_spinner);
        choosebtn = view.findViewById(R.id.chooseRoutineImgID);
        imageView = view.findViewById(R.id.routineImgID);
        savebtn = view.findViewById(R.id.routineSaveImgID);
        RoutineClassNames = new ArrayList<>();
        choosebtn.setOnClickListener(this);
        savebtn.setOnClickListener(this);
        routine_spinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onStart() {
        Query q = databaseReference.child("subjects").orderByChild("subjects");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RoutineClassNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String spinner = dataSnapshot.child("subjects").getValue(String.class);
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


        String usr = routneclsnm;


        StorageReference ref = storageReference.child("" + usr + ".jpg");

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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}