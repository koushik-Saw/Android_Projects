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

import com.example.foodapp.CategoryDetailsActivity;
import com.example.foodapp.LoginActivity;
import com.example.foodapp.R;
import com.example.foodapp.model.Category;
import com.example.foodapp.model.Favorites;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdepter extends RecyclerView.Adapter<CategoryAdepter.CategoryViewHolder> implements Filterable {

    private Context context;
    private List<Category> categoryList;
    private List<Category> list;
    private int position;
    private DatabaseReference databaseReference;
    private FirebaseUser currentuser;
    private FirebaseAuth mauth;
    private String data;
    private String key;
    private List<Favorites> favItemList;

    public CategoryAdepter() {
    }



    public CategoryAdepter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        this.list = categoryList;
    }



    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.category_row,parent,false);
        return new CategoryViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int position) {

        databaseReference = FirebaseDatabase.getInstance().getReference("favorites");


        holder.textView.setText(categoryList.get(position).getName());
        Picasso.get()
                .load(categoryList.get(position).getUrl())
                .placeholder(R.drawable.lodding)
                .fit()
                .into(holder.favimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = categoryList.get(position).getName();
                Intent intent = new Intent(context, CategoryDetailsActivity.class);
                intent.putExtra("name",name);
                context.startActivity(intent);
            }
        });

        holder.favImgborder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String name = categoryList.get(position).getName();
                String url = categoryList.get(position).getUrl();
                mauth = FirebaseAuth.getInstance();
                currentuser = mauth.getCurrentUser();
                String uid = currentuser.getUid().toString();
                key = uid+name;
                data = LoginActivity.getInstance().getData();
                Favorites favorites = new Favorites(System.currentTimeMillis(),name,url,data);
                databaseReference.child(key).setValue(favorites);
                holder.favImgborder.setVisibility(View.GONE);
                holder.favImg.setVisibility(View.VISIBLE);


            }
        });

        holder.favImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.favImg.setVisibility(View.GONE);
                holder.favImgborder.setVisibility(View.VISIBLE);

                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("favorites");
                databaseReference1.child(key).removeValue();

                Toast.makeText(context, ""+key, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if(charString.isEmpty()){
                    categoryList = list;
                }else {

                    ArrayList<Category> filterList = new ArrayList<>();
                    for(int i=0; i<list.size(); i++){
                        String s = list.get(i).getName();
                        if(s.toLowerCase().contains(charString.toLowerCase())){
                            filterList.add(list.get(i));
                        }
                    }

                    categoryList = filterList;

                }

                FilterResults results = new FilterResults();
                results.values = categoryList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                categoryList = (List<Category>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView favimg,favImgborder,favImg;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.category_rowTV);
            favimg = itemView.findViewById(R.id.category_rowIV);
            favImgborder = itemView.findViewById(R.id.fav_image_border);
            favImg = itemView.findViewById(R.id.fav_image);

        }
    }
}
