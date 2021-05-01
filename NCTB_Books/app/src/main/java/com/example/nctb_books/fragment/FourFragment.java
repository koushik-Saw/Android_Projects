package com.example.nctb_books.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nctb_books.Adapters.OnetotenBookAdapter;
import com.example.nctb_books.Model.ClassBookList;
import com.example.nctb_books.Pdf;
import com.example.nctb_books.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FourFragment extends Fragment implements OnetotenBookAdapter.onBooklinstener{


    RecyclerView recyclerView2;
    DatabaseReference databaseReference;
    OnetotenBookAdapter solutionadapter;
    ArrayList<ClassBookList> solutionlist;

    public FourFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_four, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Solutions");
        solutionlist = new ArrayList<>();

        recyclerView2 = view.findViewById(R.id.recyclerview2);
        solutionadapter = new OnetotenBookAdapter(solutionlist, this);
        return view;
    }

    @Override
    public void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                solutionlist.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        solutionlist.add(dataSnapshot1.getValue(ClassBookList.class));
                    } catch (Exception e) {

                    }

                }
                recyclerView2.setAdapter(solutionadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
    TextView g;
    String a, Booklink;
    @Override
    public void onClickItem(int position) {
        solutionlist.get(position);

        g = recyclerView2.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.bookLink);
        Booklink = g.getText().toString().trim();
        Intent i = new Intent(getContext(), Pdf.class);
        i.putExtra("Book_Link", Booklink);
        startActivity(i);
    }
}