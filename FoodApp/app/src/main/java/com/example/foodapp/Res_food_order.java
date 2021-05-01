package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.adepter.Oder_item_adapter;
import com.example.foodapp.model.Order_items;
import com.example.foodapp.model.Res_items;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Res_food_order extends AppCompatActivity implements Oder_item_adapter.OnAddCartListener {

    DatabaseReference databaseReference, databaseReference2;

    TextView name;
    private RecyclerView foodlist;
    ArrayList<Res_items> res_items;
    Oder_item_adapter oder_item_adapter;
    String restaurant;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    String uid;

    TextView item_name, item_price,item_imgurl;
    ImageView img;
    EditText item_quantity;
    String it_name, it_price, it_quantity, res_name, user, totalprice,item_img_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_food_order);

        name = findViewById(R.id.resname);
        foodlist = findViewById(R.id.item_list);
        res_items = new ArrayList<>();
        Bundle b = new Bundle();
        Intent i = this.getIntent();
        b = i.getExtras();
        restaurant = b.getString("nm");
        name.setText(restaurant);
        oder_item_adapter = new Oder_item_adapter(res_items, this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("items");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Ordered_Items");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                res_items.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    try {
                        res_items.add(dataSnapshot1.getValue(Res_items.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (oder_item_adapter != null) {
                        oder_item_adapter.getFilter().filter(restaurant);
                    }

                } catch (Exception e) {
                    Toast.makeText(Res_food_order.this, "" + e, Toast.LENGTH_SHORT).show();
                }
                foodlist.setAdapter(oder_item_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }

    @Override
    public void OnAddCartClick(int position) {
        res_items.get(position);
        item_name = foodlist.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.order_item_name);
        item_price = foodlist.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.order_item_price);
        item_quantity = foodlist.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.order_item_quantity);
        //item_imgurl = foodlist.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.it_img_url);

        it_name = item_name.getText().toString().trim();
        it_price = item_price.getText().toString().trim();
        it_quantity = item_quantity.getText().toString().trim();
        //item_img_url = item_imgurl.getText().toString().trim();

        user = LoginActivity.getInstance().getData();
        res_name = name.getText().toString().trim();

        if (it_quantity.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Quantity filed is empty", Toast.LENGTH_SHORT).show();
        } else {
            totalprice = String.valueOf(Double.valueOf(it_price) * Double.valueOf(it_quantity   ));
            String key2 = uid+res_name+it_name;

            Order_items order_items = new Order_items(user,res_name,it_name,totalprice,it_quantity);
            databaseReference2.child(key2).setValue(order_items);
            Toast.makeText(getApplicationContext(), "order Successful", Toast.LENGTH_LONG).show();
        }

    }
}