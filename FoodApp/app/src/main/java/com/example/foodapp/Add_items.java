package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodapp.model.Res_items;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Add_items extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText it_nm, it_prc, res_nm;
    Button add_it, itemimageselector;
    DatabaseReference databaseReference;
    String restu, item_nm, item_prx, key;
    private Spinner spinner;
    ImageView itemiamge;
    Uri uri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        storageReference = FirebaseStorage.getInstance().getReference("item_images");

        add_it = findViewById(R.id.add_it);
        it_nm = findViewById(R.id.it_name);
        it_prc = findViewById(R.id.it_price);
        spinner = findViewById(R.id.restaurantNames);
        itemiamge = findViewById(R.id.itemimage);
        itemimageselector = findViewById(R.id.item_image_selector);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.restaurant_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        add_it.setOnClickListener(this);
        itemimageselector.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_it) {
            additem();
        } else if (v.getId() == R.id.item_image_selector) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String parmission[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(parmission, 1001);
            } else {
                selectImage();
            }
        }
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

    private void additem() {
        item_nm = it_nm.getText().toString().trim();
        item_prx = it_prc.getText().toString().trim();
        key = databaseReference.push().getKey();

        StorageReference ref = storageReference.child(item_nm +restu+"." + getFileExtention(uri));
        ref.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        try {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while ((!uriTask.isSuccessful())) ;
                            Uri downloaduri = uriTask.getResult();
                            Res_items res_items = new Res_items(item_nm, item_prx, restu, downloaduri.toString());
                            databaseReference.child(key).setValue(res_items);
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    uri = data.getData();
                    Picasso.get()
                            .load(uri).fit()
                            .into(itemiamge);
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        restu = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}