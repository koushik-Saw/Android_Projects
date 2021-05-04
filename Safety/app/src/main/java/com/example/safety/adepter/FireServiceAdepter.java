package com.example.safety.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safety.R;
import com.example.safety.model.FireService;

import java.util.ArrayList;

public class FireServiceAdepter extends RecyclerView.Adapter<FireServiceAdepter.myviewholder> implements Filterable {

    ArrayList<FireService> list;
    ArrayList<FireService> listFiltered;
    onCallListener callListener;

    public FireServiceAdepter(ArrayList<FireService> list, onCallListener callListener) {
        this.list = list;
        this.callListener = callListener;
        this.listFiltered = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_firesation_numbers, parent, false);
        return new myviewholder(view, callListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.fr_add.setText(listFiltered.get(position).getFireAddress());
        holder.fr_num.setText(listFiltered.get(position).getFireNumber());
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listFiltered = list;
                } else {
                    ArrayList<FireService> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getFireAddress();
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
                listFiltered = (ArrayList<FireService>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fr_add, fr_num;
        onCallListener callListener;
        ImageButton cl;

        public myviewholder(@NonNull View itemView, onCallListener callListener) {
            super(itemView);
            this.callListener = callListener;
            cl = itemView.findViewById(R.id.call);
            fr_add = itemView.findViewById(R.id.fire_address);
            fr_num = itemView.findViewById(R.id.fire_number);
            cl.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            callListener.onClickItem(getAdapterPosition());
        }
    }

    public interface onCallListener {
        void onClickItem(int position);

    }
}
