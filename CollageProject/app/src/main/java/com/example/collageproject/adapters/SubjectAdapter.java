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

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.myviewholder> implements Filterable {

    ArrayList<Courses> list;
    ArrayList<Courses> listFiltered;

    onsubjectListener facultyListener;

    public SubjectAdapter(ArrayList<Courses> list, onsubjectListener facultyListener) {
        this.list = list;
        this.listFiltered = list;
        this.facultyListener = facultyListener;
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
                        String string = list.get(i).getFac_year();
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

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_course_layout, parent, false);
        return new myviewholder(view, facultyListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.facultynm.setText(listFiltered.get(position).getSubjects());
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView facultynm;
        onsubjectListener facultyListener;

        public myviewholder(@NonNull View itemView, onsubjectListener facultyListener) {
            super(itemView);
            this.facultyListener = facultyListener;
            facultynm = itemView.findViewById(R.id.coursenm);
            facultynm.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            facultyListener.onClickItem(getAdapterPosition(), v);
        }
    }

    public interface onsubjectListener {
        void onClickItem(int position, View view);
    }
}
