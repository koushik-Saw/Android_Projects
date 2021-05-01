package com.example.schoolproject.fragment.Admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AdminLoginFragment extends Fragment {


    private Button loginbtn;
    private EditText loginemail, loginpassword;
    private CheckBox showpass;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentuser;

    public AdminLoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        currentuser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentuser!=null){
            Navigation.findNavController(container).navigate(R.id.action_adminLoginFragment_to_adminDetailsFragment);

        }


        loginbtn =  view.findViewById(R.id.login);
        loginemail = view.findViewById(R.id.login_email);
        loginpassword = view.findViewById(R.id.login_password);
        showpass = view.findViewById(R.id.login_show_pass);


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


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginemail.getText().toString().trim();
                String password = loginpassword.getText().toString().trim();

                if(email.isEmpty()){
                    loginemail.setError("Enter a valid email");
                    loginemail.requestFocus();
                    return;
                }else{
                    if (password.isEmpty()) {
                        loginpassword.setError("Enter your password");
                        loginpassword.requestFocus();
                        return;
                    }else{
                        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    currentuser = FirebaseAuth.getInstance().getCurrentUser();
                                    //Intent i = new Intent(getContext(), MainActivity.class);
                                    // startActivity(i);
                                    loginemail.setText("");
                                    loginpassword.setText("");
                                    Toast.makeText(getContext(), "Login Successful!!", Toast.LENGTH_SHORT).show();
                                    Log.e("login", "Login Successful!!" );
                                    Navigation.findNavController(container).navigate(R.id.action_adminLoginFragment_to_adminDetailsFragment);
                                }else {
                                    loginemail.setText("");
                                    loginpassword.setText("");

                                    Toast.makeText(getContext(), "Wrong email and password!!", Toast.LENGTH_SHORT).show();
                                    Log.e("login", "Wrong email and password!!" );
                                }
                            }
                        });
                    }
                }
            }
        });


        return view;
    }


}