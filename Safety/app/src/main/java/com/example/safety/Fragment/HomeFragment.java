package com.example.safety.Fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.safety.MainActivity;
import com.example.safety.R;
import com.example.safety.helper.LocationViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Button fireServiceBtn, policeStationBtn, hospitalBtn;
    private CardView policeStation, fireService, hospital;
    private ImageButton sendLocationBtn;
    private LocationViewModel locationViewModel;
    String locationAddress, trustedco1,trustedco2;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference databaseReference;
    String usr, uid;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        usr = currentUser.getEmail().toString();
        uid = currentUser.getUid();

        toolbar = view.findViewById(R.id.toolbarID);
        drawerLayout = view.findViewById(R.id.drawerId);
        navigationView = view.findViewById(R.id.navigationViewId);
        policeStation = view.findViewById(R.id.policeStationCrdId);
        fireService = view.findViewById(R.id.fireServiceCrdId);
        hospital = view.findViewById(R.id.hospitalCrdId);
        fireServiceBtn = view.findViewById(R.id.fireServiceBtnId);
        hospitalBtn = view.findViewById(R.id.hospitalBtnId);
        policeStationBtn = view.findViewById(R.id.policeStationBtnId);
        sendLocationBtn = view.findViewById(R.id.sendLocationID);
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        locationViewModel.getLocationMutableLiveData().observe(getActivity(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();

                /*Log.e("location", "Latitude: "+lat );
                Log.e("location", "Longitude: "+lng );*/

                try {

                    Geocoder geocoder = new Geocoder(getActivity(),
                            Locale.getDefault());

                    List<Address> addresses = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1);

                  /*  Log.e("address", "onSuccess: "+addresses.get(0).getLatitude());
                    Log.e("address", "onSuccess: "+addresses.get(0).getLongitude());
                    Log.e("address", "onSuccess: "+addresses.get(0).getCountryName());
                    Log.e("address", "onSuccess: "+addresses.get(0).getLocality());*/
                    Log.e("address", "onSuccess: " + addresses.get(0).getAddressLine(0));
                    locationAddress = addresses.get(0).getAddressLine(0);

                    // latLngTV.setText(locationAddress);
                    Log.e("location", "address: " + locationAddress);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        sendLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("location", "onClick: ");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("Send location");
                alertDialogBuilder.setMessage("Are you sure to send your location to your trusted contact");
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String pno = trustedco1.toString();
                        String loc = locationAddress.toString();
                        Log.e("ss", "onClick: "+pno+"\n"+loc);


                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(getActivity().checkSelfPermission(Manifest.permission.SEND_SMS) ==
                                    PackageManager.PERMISSION_GRANTED){
                                smsSent();

                            }else {
                                requestPermissions(new String[] {Manifest.permission.SEND_SMS},71);
                            }
                        }
                        



                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        policeStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_policeStationFragment);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_hospitalFragment);
            }
        });


        fireService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_fireServiceFragment);
            }
        });


        fireServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_fireServiceFragment);
            }
        });


        hospitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_hospitalFragment);
            }
        });


        policeStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_homeFragment_to_policeStationFragment);
            }
        });

        return view;
    }

    private void smsSent() {

        String pno1 = trustedco1.toString();
        String pno2 = trustedco2.toString();
        String p1[] = {pno1, pno2};
        String loc = locationAddress.toString();

        try {

            SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage(pno, null,"I am in danger.Please help me.My location: \n"+ loc, null, null);

            for(String number : p1) {
                smsManager.sendTextMessage(number, null, "I am in danger.Please help me.My location: \n"+ loc, null, null);
            }
            Toast.makeText(getContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    trustedco1 = snapshot.child(uid).child("trusted_contact_1").getValue(String.class);
                    trustedco2 = snapshot.child(uid).child("trusted_contact_2").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profileId) {
            Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment).navigate(R.id.profileFragment);

        } else if (item.getItemId() == R.id.locationId) {
            Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment).navigate(R.id.locationFragment);

        } else if (item.getItemId() == R.id.aboutId) {
            Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment).navigate(R.id.aboutFragment);

        } else if (item.getItemId() == R.id.contactId) {
            Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment).navigate(R.id.contactFragment);

        } else if (item.getItemId() == R.id.privacyPolicyId) {
            Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment).navigate(R.id.privacyFragment);

        } else if (item.getItemId() == R.id.logoutId) {

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

        drawerLayout.close();

        return true;
    }
}