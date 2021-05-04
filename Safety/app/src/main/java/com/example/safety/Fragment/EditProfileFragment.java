package com.example.safety.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safety.R;
import com.example.safety.model.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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


public class EditProfileFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String uid;
    TextView user;
    String usr, nm, adrs, phn,trst;
    ImageView Editpropic;
    Button choose, saveimg;
    EditText name, address, phone, trusted;
    Uri uri;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        storageReference = FirebaseStorage.getInstance().getReference("Profile_Pics");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();

        user = view.findViewById(R.id.Email);
        Editpropic = view.findViewById(R.id.profilepic);
        choose = view.findViewById(R.id.profilepicselector);
        saveimg = view.findViewById(R.id.Saveprofile);

        usr = currentUser.getEmail().toString();
        user.setText(usr);
        name = view.findViewById(R.id.Fullname);
        address = view.findViewById(R.id.Address);
        phone = view.findViewById(R.id.Edit_Phone);
        trusted = view.findViewById(R.id.Edit_trusted);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String parmission[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(parmission, 1001);
                } else {
                    selectImage();
                }
            }
        });
        saveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_profile();
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String EditName = snapshot.child(uid).child("name").getValue(String.class);
                    String EditAddrs = snapshot.child(uid).child("address").getValue(String.class);
                    String url = snapshot.child(uid).child("profile_pic").getValue(String.class);
                    String Editphone = snapshot.child(uid).child("trusted_contact_1").getValue(String.class);
                    String trustedco = snapshot.child(uid).child("trusted_contact_2").getValue(String.class);
                    address.setText(EditAddrs);
                    name.setText(EditName);
                    phone.setText(Editphone);
                    trusted.setText(trustedco);
                    //Picasso.get().load(url).fit().into(Editpropic);

                    StorageReference imgref = storageReference.child("" + usr + ".jpg");
                    imgref.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            Editpropic.setImageBitmap(bitmap);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    private void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    private void save_profile() {

        nm = name.getText().toString().trim();
        adrs = address.getText().toString().trim();
        phn = phone.getText().toString().trim();
        trst = trusted.getText().toString().trim();

        StorageReference ref = storageReference.child("" + usr + ".jpg");


        Bitmap bitmap = ((BitmapDrawable) Editpropic.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        ref.putBytes(byteArrayOutputStream.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                try {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while ((!uriTask.isSuccessful())) ;
                    Uri downloaduri = uriTask.getResult();
                    Users users = new Users(nm, adrs, phn, usr, downloaduri.toString(),trst);
                    databaseReference.child(uid).setValue(users);
                    Toast.makeText(getContext(), "Profile Save successful", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity().getApplicationContext(), "Profile Save successful", Toast.LENGTH_SHORT).show();
                    Log.e("empty", "onSuccess: empty");

                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Profile not Save successful", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    public String getFileExtention(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {

                    uri = data.getData();
                    Picasso.get()
                            .load(uri)
                            .fit()
                            .into(Editpropic);
                }
            }
        }


    }


}