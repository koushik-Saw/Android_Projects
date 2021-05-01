package com.example.schoolproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.R;
import com.example.schoolproject.model.Sections;

import java.util.ArrayList;

public class ShowOnlineClassSecAdapter extends RecyclerView.Adapter<ShowOnlineClassSecAdapter.myviewholder> implements Filterable {

    ArrayList<Sections> sectionsList;
    ArrayList<Sections> listFiltered;
    onOnlineclasssecListener onOnlineclasssecListener;

    public ShowOnlineClassSecAdapter(ArrayList<Sections> sectionsList, onOnlineclasssecListener onOnlineclasssecListener) {
        this.sectionsList = sectionsList;
        this.listFiltered = sectionsList;
        this.onOnlineclasssecListener = onOnlineclasssecListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_online_classes_secs, parent, false);
        return new myviewholder(view, onOnlineclasssecListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.onlineclassname.setText(listFiltered.get(position).getClassnames());
        holder.onlineclassec.setText(listFiltered.get(position).getSection());
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
                    listFiltered = sectionsList;
                } else {
                    ArrayList<Sections> filteredList = new ArrayList<>();
                    for (int i = 0; i < sectionsList.size(); i++) {
                        String string = sectionsList.get(i).getClassnames();
                        if (string.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(sectionsList.get(i));
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
                listFiltered = (ArrayList<Sections>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public interface onOnlineclasssecListener {
        void onClickItem(int position);
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView onlineclassname, onlineclassec;
        Button openlinks;

        onOnlineclasssecListener onlineclassListener;

        public myviewholder(@NonNull View itemView, onOnlineclasssecListener onlineclassListener) {
            super(itemView);
            this.onlineclassListener = onlineclassListener;
            onlineclassname = itemView.findViewById(R.id.sample_online_cnlas_sec_names);
            onlineclassec = itemView.findViewById(R.id.sample_online_classSec);
            onlineclassec.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onlineclassListener.onClickItem(getAdapterPosition());
        }
    }
}
