package com.example.nctb_books;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nctb_books.Adapters.OnetotenBookAdapter;
import com.example.nctb_books.Model.ClassBookList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowBooks extends AppCompatActivity implements OnetotenBookAdapter.onBooklinstener {

    RecyclerView recyclerView1;
    DatabaseReference databaseReference;
    OnetotenBookAdapter onetotenBookAdapter;
    ArrayList<ClassBookList> classBookLists;

    Button closebn;
    String a, Booklink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_books);

        Intent intent = getIntent();
        a = intent.getStringExtra("key");
        
        //Toast.makeText(getApplicationContext(), a, Toast.LENGTH_LONG).show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Books");
        classBookLists = new ArrayList<>();
        closebn = findViewById(R.id.close);
        recyclerView1 = findViewById(R.id.recyclerview1);
        onetotenBookAdapter = new OnetotenBookAdapter(classBookLists, this);

        closebn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classBookLists.clear();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classBookLists.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        classBookLists.add(dataSnapshot1.getValue(ClassBookList.class));
                    } catch (Exception e) {

                    }
                }
                try {
                    if (onetotenBookAdapter != null) {
                        onetotenBookAdapter.getFilter().filter(a);
                        onetotenBookAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
                recyclerView1.setAdapter(onetotenBookAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        classBookLists.clear();
        finish();
        super.onBackPressed();
    }

    TextView g;
    @Override
    public void onClickItem(int position) {
        classBookLists.get(position);

        g = recyclerView1.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.bookLink);

        Booklink = g.getText().toString().trim();
       // Toast.makeText(getApplicationContext(),Booklink,Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(), Pdf.class);
        i.putExtra("Book_Link", Booklink);
        startActivity(i);
    }
}