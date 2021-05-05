package com.example.collageproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collageproject.R;
import com.example.collageproject.models.Courses;
import com.example.collageproject.models.OnlineClasses;

import java.util.ArrayList;

public class ShowOnlineClassesAdapter extends RecyclerView.Adapter<ShowOnlineClassesAdapter.myviewholder> implements Filterable {

    ArrayList<OnlineClasses> list;
    ArrayList<OnlineClasses> listFiltered;
    OnopenListener onopenListener;

    public ShowOnlineClassesAdapter(ArrayList<OnlineClasses> list, OnopenListener onopenListener) {
        this.list = list;
        this.listFiltered = list;
        this.onopenListener = onopenListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_class_layout, parent, false);
        return new myviewholder(view,onopenListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.booknames.setText(listFiltered.get(position).getSub());
        holder.boolink.setText(listFiltered.get(position).getClassLink());
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
                    ArrayList<OnlineClasses> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getFaculty();
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
                listFiltered = (ArrayList<OnlineClasses>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView booknames,boolink;
        Button openbook;
        OnopenListener onopenListener;
        public myviewholder(@NonNull View itemView,OnopenListener onopenListener) {
            super(itemView);
            this.onopenListener = onopenListener;
            booknames = itemView.findViewById(R.id.Booknames);
            boolink = itemView.findViewById(R.id.bookLink);
            openbook =itemView.findViewById(R.id.openLink);
            openbook.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onopenListener.onClickItem(getAdapterPosition(),v);
        }
    }
    public interface OnopenListener {
        void onClickItem(int position, View view);
    }
}
