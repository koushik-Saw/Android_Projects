package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.model.Category;
import com.example.foodapp.model.Users;
import com.google.android.gms.tasks.OnFailureListener;
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

import javax.security.auth.login.LoginException;

public class Edit_Profile extends AppCompatActivity implements View.OnClickListener {
    private TextView user;
    String usr, nm, adrs, phn;
    String uid;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button choose, saveimg;
    ImageView Editpropic;
    private EditText name, address, phone;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        storageReference = FirebaseStorage.getInstance().getReference("Profile_Pics");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();

        user = findViewById(R.id.Email);
        Editpropic = findViewById(R.id.profilepic);
        choose = findViewById(R.id.profilepicselector);
        saveimg = findViewById(R.id.Saveprofile);

        usr = currentUser.getEmail().toString();
        user.setText(usr);
        name = findViewById(R.id.Fullname);
        address = findViewById(R.id.Address);
        phone = findViewById(R.id.Edit_Phone);
        choose.setOnClickListener(this);
        saveimg.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String EditName = snapshot.child(uid).child("name").getValue(String.class);
                    String EditAddrs = snapshot.child(uid).child("address").getValue(String.class);
                    String url = snapshot.child(uid).child("profile_pic").getValue(String.class);
                    String Editphone = snapshot.child(uid).child("phone").getValue(String.class);
                    address.setText(EditAddrs);
                    name.setText(EditName);
                    phone.setText(Editphone);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profilepicselector) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String parmission[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(parmission, 1001);
            } else {
                selectImage();
            }

        } else if (view.getId() == R.id.Saveprofile) {
            save_profile();
        }
    }

    private void save_profile() {

        nm = name.getText().toString().trim();
        adrs = address.getText().toString().trim();
        phn = phone.getText().toString().trim();

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
                    Users users = new Users(nm, adrs, phn, usr, downloaduri.toString());
                    databaseReference.child(uid).setValue(users);
                    Toast.makeText(getApplicationContext(), "Profile Save successful", Toast.LENGTH_SHORT).show();
                    Log.e("empty", "onSuccess: empty");

                } catch (Exception e) {
                    Toast.makeText(Edit_Profile.this, "aaaaa", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public String getFileExtention(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    uri = data.getData();
                    Picasso.get()
                            .load(uri)
                            .into(Editpropic);
                }
            }
        }


    }
}
