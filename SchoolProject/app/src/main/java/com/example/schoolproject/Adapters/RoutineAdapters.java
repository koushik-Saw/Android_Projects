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

public class RoutineAdapters extends RecyclerView.Adapter<RoutineAdapters.myviewholder> {

    ArrayList<All_Classes> list;
    onClasslinstener classlinstener;

    public RoutineAdapters(ArrayList<All_Classes> list, onClasslinstener classlinstener) {
        this.list = list;
        this.classlinstener = classlinstener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_routine_layout, parent, false);
        return new myviewholder(view, classlinstener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.classno.setText(list.get(position).getClass_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onClasslinstener {
        void onClickItem(int position,View view);

    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        onClasslinstener classlinstener;
        TextView classno;

        public myviewholder(@NonNull View itemView, onClasslinstener classlinstener) {
            super(itemView);
            this.classlinstener = classlinstener;
            classno = itemView.findViewById(R.id.Routineclassno);
            classno.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            classlinstener.onClickItem(getAdapterPosition(),view);
        }
    }

}
