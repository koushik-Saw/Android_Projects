package com.example.schoolproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.R;
import com.example.schoolproject.model.OnlineClasses;

import java.util.ArrayList;

public class ShowOnlineClassAdapters extends RecyclerView.Adapter<ShowOnlineClassAdapters.myviewholder> implements Filterable {

    ArrayList<OnlineClasses> list;
    ArrayList<OnlineClasses> listfiltered;
    OnOnlineclassListener onOnlineclassListener;

    public ShowOnlineClassAdapters(ArrayList<OnlineClasses> list, OnOnlineclassListener onOnlineclassListener) {
        this.list = list;
        this.listfiltered = list;
        this.onOnlineclassListener = onOnlineclassListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_show_online_class, parent, false);
        return new myviewholder(view, onOnlineclassListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.showonlineclassecssubs.setText(listfiltered.get(position).getOnline_class_sub());
        holder.cllinks.setText(listfiltered.get(position).getOnline_class_link());
    }

    @Override
    public int getItemCount() {
        return listfiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listfiltered = list;
                } else {
                    ArrayList<OnlineClasses> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getOnline_class_name_sec_sub();
                        if (string.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(list.get(i));
                        }
                    }

                    listfiltered = filteredList;
                }

                final FilterResults filterResults = new FilterResults();
                filterResults.values = listfiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                listfiltered = (ArrayList<OnlineClasses>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView showonlineclassecssubs,cllinks;
        OnOnlineclassListener onOnlineclassListener;

        public myviewholder(@NonNull View itemView, OnOnlineclassListener onOnlineclassListener) {
            super(itemView);
            this.onOnlineclassListener = onOnlineclassListener;
            showonlineclassecssubs = itemView.findViewById(R.id.sample_online_class_names);
            cllinks = itemView.findViewById(R.id.sample_online_class_links);
            showonlineclassecssubs.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onOnlineclassListener.onClickItem(getAdapterPosition());
        }
    }

    public interface OnOnlineclassListener {
        void onClickItem(int position);
    }
}
