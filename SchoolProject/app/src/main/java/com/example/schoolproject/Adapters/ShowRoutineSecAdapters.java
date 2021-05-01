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

public class ShowRoutineSecAdapters extends RecyclerView.Adapter<ShowRoutineSecAdapters.myviewholder> implements Filterable {

    ArrayList<Sections> list;
    ArrayList<Sections> listFiltered;
    onRoutinesecListener routinesecListener;

    public ShowRoutineSecAdapters(ArrayList<Sections> list, onRoutinesecListener routinesecListener) {
        this.list = list;
        this.listFiltered = list;
        this.routinesecListener = routinesecListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_routine_sec, parent, false);
        return new myviewholder(view,routinesecListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.routineclassname.setText(listFiltered.get(position).getClassnames());
        holder.routineclasssec.setText(listFiltered.get(position).getSection());
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
                    ArrayList<Sections> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getClassnames();
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
                listFiltered = (ArrayList<Sections>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView routineclassname, routineclasssec;
        Button opensecs;
        onRoutinesecListener routinesecListener;
        public myviewholder(@NonNull View itemView,onRoutinesecListener routinesecListener) {
            super(itemView);
            this.routinesecListener= routinesecListener;
            routineclassname = itemView.findViewById(R.id.sample_routine_cnlas_sec_names);
            routineclasssec = itemView.findViewById(R.id.sample_routine_classSec);
            routineclasssec.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            routinesecListener.onClickItem(getAdapterPosition(),v);
        }
    }
    public interface onRoutinesecListener {
        void onClickItem(int position,View view);
    }

}
