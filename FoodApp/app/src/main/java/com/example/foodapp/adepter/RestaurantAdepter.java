package com.example.foodapp.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdepter extends RecyclerView.Adapter<RestaurantAdepter.RestaurantViewHolder> {

    private Context context;
    private List<Restaurant> restaurantList;
    private OnresClick onresClick;

    public RestaurantAdepter(Context context, List<Restaurant> restaurantList, OnresClick onresClick) {
        this.context = context;
        this.onresClick = onresClick;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturent_row, parent, false);
        return new RestaurantViewHolder(v, onresClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.nameTV.setText(restaurantList.get(position).getName());
        holder.ratingBar.setRating(Float.parseFloat(restaurantList.get(position).getRating()));
        Picasso.get()
                .load(restaurantList.get(position).getUrl())
                .placeholder(R.drawable.lodding)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }


    class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTV;
        RatingBar ratingBar;
        ImageView imageView;
        OnresClick onresClick;
        public RestaurantViewHolder(@NonNull View itemView, OnresClick onresClick) {
            super(itemView);
            imageView = itemView.findViewById(R.id.restaurantIV);
            ratingBar = itemView.findViewById(R.id.restaurantRB);
            nameTV = itemView.findViewById(R.id.restaurantNameTV);
            this.onresClick = onresClick;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onresClick.onResClick(getAdapterPosition());
        }
    }

    public interface OnresClick{
        void onResClick(int position);
    }
}
