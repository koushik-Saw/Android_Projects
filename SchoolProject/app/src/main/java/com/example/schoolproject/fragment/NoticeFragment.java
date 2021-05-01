package com.example.schoolproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.schoolproject.Adapters.NoticeAdapters;
import com.example.schoolproject.R;
import com.example.schoolproject.model.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticeFragment extends Fragment implements NoticeAdapters.onNoticeListener {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    NoticeAdapters noticeAdapters;
    ArrayList<Notice> notices;

    public NoticeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notice");
        notices = new ArrayList<Notice>();
        recyclerView = view.findViewById(R.id.notice_recycleId);
        noticeAdapters = new NoticeAdapters(notices,this);
        return view;
    }

    @Override
    public void onStart() {
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
        super.onStart();
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