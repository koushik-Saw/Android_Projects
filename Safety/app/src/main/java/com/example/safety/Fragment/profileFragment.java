package com.example.safety.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safety.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
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


public class profileFragment extends Fragment {

    TextView user,nam,addrrrss,trstd;
    String usr,uid;

    ImageView propic;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    MaterialButton edit_profile;
    MaterialButton lgout;

    public profileFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        user = view.findViewById(R.id.profileusername);
        addrrrss = view.findViewById(R.id.adrss);
        nam = view.findViewById(R.id.full_nm);
        propic = view.findViewById(R.id.prfile_pic);
        trstd = view.findViewById(R.id.trustedcon);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        storageReference = FirebaseStorage.getInstance().getReference("Profile_Pics");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        usr = currentUser.getEmail().toString();
        uid = currentUser.getUid();
        user.setText(usr);

        edit_profile = view.findViewById(R.id.Edit_Profile);
        lgout = view.findViewById(R.id.logout);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment).navigate(R.id.editProfileFragment);
            }
        });

        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AuthUI.getInstance()
                        .signOut(getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                FirebaseAuth.getInstance().signOut();
                                Navigation.findNavController(getActivity(),
                                        R.id.nav_host_fragment).navigate(R.id.authFragment);
                            }
                        });

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String Name = snapshot.child(uid).child("name").getValue(String.class);
                    String Addrs = snapshot.child(uid).child("address").getValue(String.class);
                    String url = snapshot.child(uid).child("profile_pic").getValue(String.class);
                    String trustedco = snapshot.child(uid).child("trusted_contact").getValue(String.class);
                    addrrrss.setText(Addrs);
                    nam.setText(Name);
                    Picasso.get().load(url).fit().into(propic);
                    trstd.setText(trustedco);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}