package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foodapp.adepter.RestaurantAdepter;
import com.example.foodapp.model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RestaurantAdepter.OnresClick {

    private RecyclerView restaurantRV;
    private RestaurantAdepter adepter;
    private List<Restaurant> restaurantList;
    private DatabaseReference databaseReference;
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container != null) {
            container.removeAllViews();
        }


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        restaurantRV = view.findViewById(R.id.restaurantRV);
        restaurantList = new ArrayList<>();
        restaurantRV.hasFixedSize();
        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurant_image");

        adepter = new RestaurantAdepter(getActivity(), restaurantList, this);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurantList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    restaurantList.add(restaurant);
                }

                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                restaurantRV.setLayoutManager(llm);
                restaurantRV.setAdapter(adepter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public void onResClick(int position) {
        restaurantList.get(position);
        TextView res_name = restaurantRV.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.restaurantNameTV);
        String res_nm = res_name.getText().toString().trim();
        Intent i = new Intent(getContext(), Res_food_order.class);
        i.putExtra("nm", res_nm);
        startActivity(i);
    }
}