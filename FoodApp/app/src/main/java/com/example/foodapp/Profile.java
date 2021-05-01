package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private TextView user,nam,addrrrss;
    String usr,uid;
    private Button lgot,edtprfl;
    private ImageView propic;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = findViewById(R.id.profileusername);
        addrrrss = findViewById(R.id.adrss);
        nam = findViewById(R.id.full_nm);
        propic = findViewById(R.id.prfile_pic);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        storageReference = FirebaseStorage.getInstance().getReference("Profile_Pics");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        usr = currentUser.getEmail().toString();
        uid = currentUser.getUid();
        user.setText(usr);

        lgot = findViewById(R.id.logout);
        edtprfl = findViewById(R.id.Edit_Profile);

        lgot.setOnClickListener(this);
        edtprfl.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String Name = snapshot.child(uid).child("name").getValue(String.class);
                    String Addrs = snapshot.child(uid).child("address").getValue(String.class);
                    String url = snapshot.child(uid).child("profile_pic").getValue(String.class);
                    addrrrss.setText(Addrs);
                    nam.setText(Name);
                    Picasso.get().load(url).fit().into(propic);
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
        if(view.getId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(Profile.this,LoginActivity.class));
        }
        else if (view.getId()==R.id.Edit_Profile){
            Intent i = new Intent(Profile.this,Edit_Profile.class);
            startActivity(i);
            finish();
        }

    }
}