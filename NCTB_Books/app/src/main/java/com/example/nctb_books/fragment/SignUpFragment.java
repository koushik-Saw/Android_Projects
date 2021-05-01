package com.example.nctb_books.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.example.nctb_books.Model.Users;
import com.example.nctb_books.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class SignUpFragment extends Fragment {

    EditText signupfullname, signupemail, signuppassword, signupconfirmpassword, signupphone, signupaddress;
    Button signupbtn;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;

    public SignUpFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        signupfullname = view.findViewById(R.id.full_name);
        signupemail = view.findViewById(R.id.sign_up_email);
        signuppassword = view.findViewById(R.id.sign_up_password);
        signupconfirmpassword = view.findViewById(R.id.sign_up_confirm_password);
        signupphone = view.findViewById(R.id.sign_up_phone);
        signupaddress = view.findViewById(R.id.sign_up_address);
        signupbtn = view.findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signupemail.getText().toString();
                String password = signuppassword.getText().toString();
                String phone = signupphone.getText().toString();
                String address = signupaddress.getText().toString();

                String fullname = signupfullname.getText().toString();
                String RegiPass = signuppassword.getText().toString();
                String RegiCpass = signupconfirmpassword.getText().toString();

                if (fullname.isEmpty()) {
                    signupfullname.setError("Enter a Name");
                } else {
                    if (email.isEmpty()) {
                        signupemail.setError("Enter a Email");
                    } else {
                        if (RegiPass.isEmpty()) {
                            signuppassword.setError("Enter a Password");
                        } else {
                            if (RegiCpass.isEmpty()) {
                                signupconfirmpassword.setError("Confirm Your Password");
                            } else {
                                if (!RegiCpass.equals(RegiPass)) {
                                    signupconfirmpassword.setError("Password didn't match");
                                    signuppassword.setText("");
                                    signupconfirmpassword.setText("");
                                } else {

                                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                            DatabaseReference user = firebaseDatabase.getReference().child("User").child(firebaseAuth.getUid());
                                            Users users = new Users(fullname, phone, email, address);
                                            user.setValue(users);
                                            Toast.makeText(getContext(), "Successfully Registered..!", Toast.LENGTH_SHORT).show();

                                            signupfullname.setText("");
                                            signupemail.setText("");
                                            signupphone.setText("");
                                            signupaddress.setText("");
                                            signuppassword.setText("");
                                            signupconfirmpassword.setText("");
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        });
        return view;
    }
}