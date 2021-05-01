package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.adepter.Ordered_item_adapter;
import com.example.foodapp.model.Ordered_items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;


public class CartFragment extends Fragment {

    ArrayList<Ordered_items> ordered_items_List;
    RecyclerView recyclerView;
    Ordered_item_adapter ordered_item_adapter;
    String user;
    private Button confirmBtn;
    TextView textView;
    DatabaseReference databaseReference;

    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view,bundle);
        databaseReference =  FirebaseDatabase.getInstance().getReference("Ordered_Items");
        recyclerView = view.findViewById(R.id.item_list);
        ordered_items_List = new ArrayList<>();
        confirmBtn = view.findViewById(R.id.confirm);
        user = LoginActivity.getInstance().getData();

        ordered_item_adapter = new Ordered_item_adapter(getContext(),ordered_items_List);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(ordered_items_List.isEmpty()){
                   try {
                       Toast.makeText(getApplicationContext(),"Order food",Toast.LENGTH_SHORT).show();


                   }catch (Exception e){
                       Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_SHORT).show();

                   }
               }else {

                   ordered_items_List.clear();
                   FirebaseDatabase.getInstance().getReference().child("Ordered_Items").setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Toast.makeText(getApplicationContext(), "Order Successful", Toast.LENGTH_LONG).show();

                       }
                   });

               }
            }
        });
    }

    @Override
    public void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ordered_items_List.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        Ordered_items ordered_items = dataSnapshot1.getValue(Ordered_items.class);
                        ordered_items.setKey(dataSnapshot1.getKey());
                        ordered_items_List.add(ordered_items);
                    } catch (Exception e) {
                    }
                }

                try{
                    if(ordered_item_adapter!=null){
                        ordered_item_adapter.getFilter().filter(user);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                recyclerView.setAdapter(ordered_item_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }


}