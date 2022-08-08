package com.example.collageproject.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collageproject.R;

import com.example.collageproject.adapters.NoticeAdepter;
import com.example.collageproject.models.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticeFragment extends Fragment implements NoticeAdepter.onNoticeListener{

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    NoticeAdepter noticeAdapters;
    ArrayList<Notice> notices;

    public NoticeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notice");
        notices = new ArrayList<Notice>();
        recyclerView = view.findViewById(R.id.notice_recycleId);
        noticeAdapters = new NoticeAdepter(notices,this);
        
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notices.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    try {
                        notices.add(dataSnapshot1.getValue(Notice.class));
                    } catch (Exception e) {

                    }
                }
                recyclerView.setAdapter(noticeAdapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void onClickItem(int position, View view) {

        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
        Log.e("notice", "onClickItem: "+position );

        String noticeTitle = notices.get(position).getNotice_title();
        String noticeMessage = notices.get(position).getNotice_body();
        Log.e("notice", "onClickItem: "+noticeTitle );
        Log.e("notice", "onClickItem: "+noticeMessage );

        Bundle bundle = new Bundle();
        bundle.putString("noticeTitle", noticeTitle);
        bundle.putString("noticeMessage", noticeMessage);
        Navigation.findNavController(view).navigate(R.id.action_noticeFragment_to_noticeDetailsFragment,bundle);

    }
}