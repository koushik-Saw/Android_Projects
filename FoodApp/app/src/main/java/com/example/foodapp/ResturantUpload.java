package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.foodapp.model.Category;
import com.example.foodapp.model.Restaurant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResturantUpload extends AppCompatActivity implements View.OnClickListener {

    Button choosebtn,savebtn,disbtn;
    RatingBar ratingBar;
    EditText imgname;
    ImageView imageView;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Uri uri;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_upload);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurant_image");
        storageReference = FirebaseStorage.getInstance().getReference("Restaurant_image");
        choosebtn = findViewById(R.id.chooseImgID);
        imgname = findViewById(R.id.editImgnameID);
        imageView = findViewById(R.id.pasImgID);
        savebtn = findViewById(R.id.saveImgID);
        disbtn = findViewById(R.id.displayImgID);
        ratingBar = findViewById(R.id.restaurantUploadRB);



        choosebtn.setOnClickListener(this);
        savebtn.setOnClickListener(this);
        disbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.chooseImgID){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                String parmission[]={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(parmission,1001);
            }else {
                selectImage();
            }

        }else  if(view.getId()==R.id.saveImgID){

            saveImg();

        }

    }

    private void saveImg() {

        Toast.makeText(getApplicationContext(),"Image Uploading..",Toast.LENGTH_SHORT).show();
        final String rating = String.valueOf(ratingBar.getRating());
        final String name = imgname.getText().toString();
        if(name.isEmpty()){
            imgname.setError("Enter email");
            imgname.requestFocus();
            return;
        }

        StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtention(uri));

        ref.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while ((!uriTask.isSuccessful()));
                        Uri downloaduri = uriTask.getResult();

                        String key =databaseReference.push().getKey() ;
                        Restaurant restaurant = new Restaurant(downloaduri.toString(),name,rating);
                        databaseReference.child(key).setValue(restaurant);

                       /* Category category = new Category(name,downloaduri.toString());
                        databaseReference.child(key).setValue(category);*/

                        Toast.makeText(getApplicationContext(),"Image store successful",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),"Image store not successful",Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    public String getFileExtention(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode==RESULT_OK){
                if(data!=null){

                    uri = data.getData();
                    Picasso.get()
                            .load(uri)
                            .into(imageView);



                }
            }
        }


    }
}