package com.example.collageproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collageproject.R;
import com.example.collageproject.models.Courses;

import java.util.ArrayList;

public class Courseadapter extends RecyclerView.Adapter<Courseadapter.myviewholder> implements Filterable {

    ArrayList<Courses> list;
    ArrayList<Courses> listFiltered;
    onRoutineCourseListener routineCourseListener;

    public Courseadapter(ArrayList<Courses> list, onRoutineCourseListener routineCourseListener) {
        this.list = list;
        this.listFiltered = list;
        this.routineCourseListener = routineCourseListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_course_layout,parent,false);
        return new myviewholder(view,routineCourseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.course_nm.setText(listFiltered.get(position).getFaculty());
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = list;
                } else {
                    ArrayList<Courses> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getCourse_name();
                        if (string.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(list.get(i));
                        }
                    }

                    listFiltered = filteredList;
                }

                final FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                listFiltered = (ArrayList<Courses>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView course_nm;
        onRoutineCourseListener routineCourseListener;
        public myviewholder(@NonNull View itemView,onRoutineCourseListener routineCourseListener) {
                super(itemView);
                this.routineCourseListener = routineCourseListener;
                course_nm = itemView.findViewById(R.id.coursenm);
                course_nm.setOnClickListener(this);
            }

        @Override
        public void onClick(View v) {
            routineCourseListener.onClickItem(getAdapterPosition(),v);
        }
    }

    public interface onRoutineCourseListener {
        void onClickItem(int position,View view);
    }
}
