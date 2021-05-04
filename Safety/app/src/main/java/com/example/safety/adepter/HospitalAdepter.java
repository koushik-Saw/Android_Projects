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
import com.example.safety.model.Hospital;

import java.util.ArrayList;

public class HospitalAdepter extends RecyclerView.Adapter<HospitalAdepter.myviewholder> implements Filterable {


    ArrayList<Hospital> list;
    ArrayList<Hospital> listFiltered;
    onCallListener callListener;

    public HospitalAdepter(ArrayList<Hospital> list, onCallListener callListener) {
        this.list = list;
        this.listFiltered = list;
        this.callListener = callListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_hospital_numbers, parent, false);
        return new myviewholder(view, callListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.hs_address.setText(listFiltered.get(position).getHospitalAddress());
        holder.hs_name.setText(listFiltered.get(position).getHospitalName());
        holder.hs_phone.setText(listFiltered.get(position).getHospitalNumber());
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
                    ArrayList<Hospital> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getHospitalAddress();
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
                listFiltered = (ArrayList<Hospital>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        onCallListener callListener;
        TextView hs_name, hs_address, hs_phone;
        ImageButton hscl;

        public myviewholder(@NonNull View itemView, onCallListener callListener) {
            super(itemView);
            this.callListener = callListener;
            hscl = itemView.findViewById(R.id.call);
            hs_address = itemView.findViewById(R.id.hospital_address);
            hs_name = itemView.findViewById(R.id.hospital_name);
            hs_phone = itemView.findViewById(R.id.hospital_number);
            hscl.setOnClickListener(this);
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
