package com.example.foodapp;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.adepter.CategoryAdepter;
import com.example.foodapp.adepter.FavoritesAdepter;
import com.example.foodapp.model.Favorites;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesAdepter adepter;
    private List<Favorites> favoritesList;
    private DatabaseReference databaseReference;
    private String filter;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }   
        return inflater.inflate(R.layout.fragment_favorites, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filter = LoginActivity.getInstance().getData();

        databaseReference = FirebaseDatabase.getInstance().getReference("favorites");
        recyclerView = view.findViewById(R.id.favoritesRV);
        favoritesList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoritesList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Favorites favorites = snapshot1.getValue(Favorites.class);
                    favorites.setKey(snapshot1.getKey());
                    favoritesList.add(favorites);
                }

                adepter = new FavoritesAdepter(getContext(), favoritesList);
                GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
                recyclerView.setLayoutManager(glm);

                if (adepter != null) {
                    adepter.getFilter().filter(filter);
                    recyclerView.setAdapter(adepter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();

            }
        });


    }

}