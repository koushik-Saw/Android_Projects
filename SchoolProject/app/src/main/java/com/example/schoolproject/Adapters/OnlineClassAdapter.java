package com.example.schoolproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.R;
import com.example.schoolproject.model.All_Classes;

import java.util.ArrayList;

public class OnlineClassAdapter extends RecyclerView.Adapter<OnlineClassAdapter.myviewholder> {

    ArrayList<All_Classes> list;
    OnClassclick onClassclick;

    public OnlineClassAdapter(ArrayList<All_Classes> list, OnClassclick onClassclick) {
        this.list = list;
        this.onClassclick = onClassclick;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_online_class, parent, false);
        return new myviewholder(view,onClassclick);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.onl_classname.setText(list.get(position).getClass_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
TextView onl_classname;
OnClassclick onClassclick;
        public myviewholder(@NonNull View itemView,OnClassclick onClassclick) {
            super(itemView);
            this.onClassclick = onClassclick;
            onl_classname = itemView.findViewById(R.id.onlineclassno);
            onl_classname.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onClassclick.onClickItem(getAdapterPosition());
        }
    }
    public interface OnClassclick {
        void onClickItem(int position);
    }
}
