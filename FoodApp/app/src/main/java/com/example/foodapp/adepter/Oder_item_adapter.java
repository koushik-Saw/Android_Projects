package com.example.foodapp.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Res_items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Oder_item_adapter extends RecyclerView.Adapter<Oder_item_adapter.myviewholder> implements Filterable {

    ArrayList<Res_items> list;
    ArrayList<Res_items> listFiltered;
    private OnAddCartListener onAddCartListener;

    public Oder_item_adapter(ArrayList<Res_items> list, OnAddCartListener onAddCartListener) {
        this.list = list;
        this.listFiltered = list;
        this.onAddCartListener = onAddCartListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_res_items, viewGroup, false);
        return new myviewholder(view, onAddCartListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.name.setText(listFiltered.get(position).getItem_name());
        holder.price.setText(listFiltered.get(position).getItem_price());
        Picasso.get()
                .load(listFiltered.get(position).getItem_images())
                .placeholder(R.drawable.lodding)
                .fit()
                .into(holder.it_im);
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
                    ArrayList<Res_items> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getRestaurant_name();
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
                listFiltered = (ArrayList<Res_items>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView price, quantity, name, cart;
        OnAddCartListener onAddCartListener;
        ImageView it_im;

        public myviewholder(@NonNull View itemView, OnAddCartListener onAddCartListener) {
            super(itemView);
            this.onAddCartListener = onAddCartListener;
            price = itemView.findViewById(R.id.order_item_price);
            name = itemView.findViewById(R.id.order_item_name);
            quantity = itemView.findViewById(R.id.order_item_quantity);
            cart = itemView.findViewById(R.id.add_to_cart);
            it_im = itemView.findViewById(R.id.it_img);
            cart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAddCartListener.OnAddCartClick(getAdapterPosition());
        }
    }

    public interface OnAddCartListener {
        void OnAddCartClick(int position);
    }

}
