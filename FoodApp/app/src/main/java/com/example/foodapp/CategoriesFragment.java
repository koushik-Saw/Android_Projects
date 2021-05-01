package com.example.foodapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodapp.adepter.CategoryAdepter;
import com.example.foodapp.adepter.RestaurantAdepter;
import com.example.foodapp.model.Category;
import com.example.foodapp.model.Favorites;
import com.example.foodapp.model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CategoriesFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private DatabaseReference databaseReference;
    private CategoryAdepter adepter;


    public CategoriesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("Category");
        searchView = view.findViewById(R.id.categorySearchId);
        recyclerView = view.findViewById(R.id.categoryRV);
        categoryList = new ArrayList<>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                categoryList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   Category category = dataSnapshot.getValue(Category.class);
                    categoryList.add(category);
                }

                adepter = new CategoryAdepter(getActivity(),categoryList);
                GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
                recyclerView.setLayoutManager(glm);
                recyclerView.setAdapter(adepter);
                Favorites favorites = new Favorites();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        if(searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    if(adepter!=null){
                        adepter.getFilter().filter(s);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if(adepter!=null){
                        adepter.getFilter().filter(s);
                    }
                    return true;
                }
            });
        }


    }
}