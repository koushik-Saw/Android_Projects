package com.example.foodapp.adepter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.FavorietsDetailsActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Favorites;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdepter extends RecyclerView.Adapter<FavoritesAdepter.FavoriteViewHolder> implements Filterable {

    private Context context;
    private List<Favorites> favoritesList;
    private DatabaseReference databaseReference;
    private List<Favorites> list;

    public FavoritesAdepter() {
    }

    public FavoritesAdepter(Context context, List<Favorites> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
        this.list = favoritesList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.category_row,parent,false);
        return new FavoriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, final int position) {

        databaseReference = FirebaseDatabase.getInstance().getReference("favorites");

        holder.favImgborder.setVisibility(View.GONE);
        holder.favImg.setVisibility(View.VISIBLE);
        holder.textView.setText(favoritesList.get(position).getName());
        Picasso.get()
                .load(favoritesList.get(position).getUrl())
                .placeholder(R.drawable.lodding)
                .fit()
                .into(holder.imageView);

        holder.favImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String key = favoritesList.get(position).getKey();
               databaseReference.child(key).removeValue();
                Toast.makeText(context, "Remove", Toast.LENGTH_SHORT).show();

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String name =  favoritesList.get(position).getName();
               Intent intent = new Intent(context, FavorietsDetailsActivity.class);
               intent.putExtra("name",name);
               context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();
                if(charString.isEmpty()){
                    favoritesList = list;
                }else {
                    ArrayList<Favorites> filterlist = new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        String s = list.get(i).getEmail();
                        if(s.toLowerCase().contains(charString.toLowerCase())){
                            filterlist.add(list.get(i));
                        }
                    }

                    favoritesList = filterlist;
                }

                FilterResults results = new FilterResults();
                results.values = favoritesList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                favoritesList = (List<Favorites>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView,favImgborder,favImg;
        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.category_rowTV);
            imageView = itemView.findViewById(R.id.category_rowIV);
            favImgborder = itemView.findViewById(R.id.fav_image_border);
            favImg = itemView.findViewById(R.id.fav_image);

        }
    }
}
