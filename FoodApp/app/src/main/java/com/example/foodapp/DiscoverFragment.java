package com.example.foodapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


public class DiscoverFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMapLongClickListener {


    GoogleMap map;
    MapView mapView;
    FusedLocationProviderClient providerClient;

    private LatLng myLocation = null;

    public DiscoverFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mapView = view.findViewById(R.id.mapViewId);
        providerClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 111);
            return;
        } else {
            map.setMyLocationEnabled(true);

        }
        map.setOnMapLongClickListener(this);
        map.getUiSettings().setZoomControlsEnabled(true);
        getDeviceCurrentLocation();


       /* LatLng sydney = new LatLng(-34,151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in sydney"));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));*/
        //   map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));

    }


    private void getDeviceCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
            return;
        }
        providerClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            return;
                        }

                        myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f));
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f));

                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 111 && requestCode ==11){
            if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED){
                getDeviceCurrentLocation();
                map.setMyLocationEnabled(true);
                //Toast.makeText(getContext(), "fdfd", Toast.LENGTH_SHORT).show();
            }
        }


    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        map.addMarker(new MarkerOptions().position(latLng).title(""));
    }



   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111 || requestCode ==11){
            if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED){
                  map.setMyLocationEnabled(true);
                getDeviceCurrentLocation();
                Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}