package com.example.nctb_books.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nctb_books.MainActivity;
import com.example.nctb_books.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    TextView singupbt,forgotpass;
    Button loginbtn;
    EditText loginemail, loginpassword;
    CheckBox showpass;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentuser;
    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        singupbt = view.findViewById(R.id.sign_up);
        loginbtn =  view.findViewById(R.id.login);
        loginemail = view.findViewById(R.id.login_email);
        loginpassword = view.findViewById(R.id.login_password);
        showpass = view.findViewById(R.id.login_show_pass);
        forgotpass = view.findViewById(R.id.login_forgot_pass);



        singupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(container).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginemail.getText().toString().trim();
                String password = loginpassword.getText().toString().trim();

                if(email.isEmpty()){
                    loginemail.setError("Enter a valid email");
                }else{
                    if (password.isEmpty()) {
                        loginpassword.setError("Enter your password");
                    }else{
                        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //Intent i = new Intent(getContext(), MainActivity.class);
                                   // startActivity(i);
                                    Navigation.findNavController(container).navigate(R.id.action_loginFragment_to_homeFragment);
                                }
                            }
                        });
                    }
                }
            }
        });

        showpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    loginpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    loginpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}