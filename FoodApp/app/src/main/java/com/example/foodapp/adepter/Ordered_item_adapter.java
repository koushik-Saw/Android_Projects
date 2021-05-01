package com.example.foodapp.adepter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.model.Ordered_items;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Ordered_item_adapter extends RecyclerView.Adapter<Ordered_item_adapter.OrdereditemViewHolder> implements Filterable {

    private Context context;
    private List<Ordered_items> ordered_itemsList;
    private List<Ordered_items> list;
    private  DatabaseReference databaseReference;


    public Ordered_item_adapter(Context context,List<Ordered_items> ordered_itemsList) {
        this.context = context;
        this.ordered_itemsList = ordered_itemsList;
        this.list = ordered_itemsList;
    }

    @NonNull
    @Override
    public OrdereditemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_ordered_items, parent, false);
        return new OrdereditemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdereditemViewHolder holder, final int position) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Ordered_Items");

        holder.itemname.setText(ordered_itemsList.get(position).getOrder_item_name());
        holder.itemquantity.setText(ordered_itemsList.get(position).getOrder_item_quantity());
        holder.itemprice.setText(ordered_itemsList.get(position).getOrder_item_price());
        holder.resname.setText(ordered_itemsList.get(position).getOrder_Rest_name());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ordered_items selectedItem = ordered_itemsList.get(position);
                String key = selectedItem.getKey();
                databaseReference.child(key).removeValue();
                Toast.makeText(context, ""+key, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
       return ordered_itemsList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if(charString.isEmpty()){
                    ordered_itemsList = list;
                }else {

                    ArrayList<Ordered_items> filterList = new ArrayList<>();
                    for(int i=0; i<list.size(); i++){
                        String s = list.get(i).getOrder_user();
                        if(s.toLowerCase().contains(charString.toLowerCase())){
                            filterList.add(list.get(i));
                        }
                    }

                    ordered_itemsList = filterList;

                }

                FilterResults results = new FilterResults();
                results.values = ordered_itemsList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ordered_itemsList = (List<Ordered_items>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    class OrdereditemViewHolder extends RecyclerView.ViewHolder {

        TextView itemname,itemquantity,itemprice,resname;
        ImageButton deleteButton;
        public OrdereditemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.ordered_item_name);
            itemquantity = itemView.findViewById(R.id.ordered_item_quantity);
            itemprice = itemView.findViewById(R.id.ordered_item_price);
            deleteButton = itemView.findViewById(R.id.singleitemdelete);
            resname = itemView.findViewById(R.id.ordered_rest_name);

        }
    }
}
