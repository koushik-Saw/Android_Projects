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
import com.example.safety.model.PoliceStation;

import java.util.ArrayList;

public class PoliceStationAdepter extends RecyclerView.Adapter<PoliceStationAdepter.myviewholder> implements Filterable {

    ArrayList<PoliceStation> list;
    ArrayList<PoliceStation> listFiltered;
    onCallListener callListener;

    public PoliceStationAdepter(ArrayList<PoliceStation> list, onCallListener callListener) {
        this.list = list;
        this.listFiltered = list;
        this.callListener = callListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_policestation_numbers, parent, false);
        return new myviewholder(view, callListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.ps_address.setText(listFiltered.get(position).getPoliceStationAddress());
        holder.ps_name.setText(listFiltered.get(position).getPoliceStationName());
        holder.ps_phone.setText(listFiltered.get(position).getPoliceStationNumber());
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
                    ArrayList<PoliceStation> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getPoliceStationAddress();
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
                listFiltered = (ArrayList<PoliceStation>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        onCallListener callListener;
        TextView ps_name, ps_address, ps_phone;
        ImageButton cl;

        public myviewholder(@NonNull View itemView, onCallListener callListener) {
            super(itemView);
            this.callListener = callListener;
            ps_name = itemView.findViewById(R.id.police_station_name);
            ps_address = itemView.findViewById(R.id.police_address);
            ps_phone = itemView.findViewById(R.id.police_station_number);
            cl = itemView.findViewById(R.id.call);
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
