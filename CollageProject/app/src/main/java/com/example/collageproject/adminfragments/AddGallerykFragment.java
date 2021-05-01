package com.example.collageproject.adminfragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.collageproject.R;
import com.example.collageproject.models.GalleryImageUpload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class AddGallerykFragment extends Fragment implements View.OnClickListener{

    private Button choosebtn,savebtn,disbtn;
    private ImageView imageView;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private static final int IMAGE_REQUEST = 17;
    private Uri imageUri;


    public AddGallerykFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_galleryk, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Gallery Image");
        storageReference = FirebaseStorage.getInstance().getReference("Gallery Image");

        choosebtn = view.findViewById(R.id.chooseGalleryImgID);
        imageView = view.findViewById(R.id.galleryImgID);
        savebtn = view.findViewById(R.id.gallerySaveImgID);
        disbtn = view.findViewById(R.id.galleryDisplayImgID);

        choosebtn.setOnClickListener(this);
        savebtn.setOnClickListener(this);
        disbtn.setOnClickListener(this);

        
        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.chooseGalleryImgID:

                openFileChooser();
                break;

            case R.id.gallerySaveImgID:
                saveData();

                break;

            case R.id.galleryDisplayImgID:
                Toast.makeText(getContext(), "eee", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment).navigate(R.id.galleryFragment);

                break;
        }


    }

    private void saveData() {

        StorageReference ref = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

        ref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(getContext(), "Image is stored successfully!!", Toast.LENGTH_SHORT).show();

                Task<Uri> galleryImageUploadTask = taskSnapshot.getStorage().getDownloadUrl();

                while (!galleryImageUploadTask.isSuccessful());

                Uri downloadUri = galleryImageUploadTask.getResult();

                GalleryImageUpload imageUpload = new GalleryImageUpload(downloadUri.toString());

                String UploadID = databaseReference.push().getKey();
                databaseReference.child(UploadID).setValue(imageUpload);

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Image is not stored successfully!!", Toast.LENGTH_SHORT).show();


                    }
                });
    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }


    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST &&
                resultCode == getActivity().RESULT_OK &&
                data!=null && data.getData()!=null){

            imageUri= data.getData();

            Picasso.get()
                    .load(imageUri).into(imageView);

        }


    }
}