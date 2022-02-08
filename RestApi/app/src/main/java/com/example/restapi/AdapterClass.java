package com.example.restapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.Adapterclassviewholder> {

    List<Postpojo> postpojos;
    private Context context;

    public AdapterClass(List<Postpojo> postpojos, Context context) {
        this.postpojos = postpojos;
        this.context = context;
    }

    public AdapterClass() {

    }

    @NonNull
    @Override
    public Adapterclassviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample,parent,false);
        return new Adapterclassviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterclassviewholder holder, int position) {
        holder.textView1.setText(postpojos.get(position).getTitle());
        holder.textView2.setText(postpojos.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return postpojos.size();
    }

    static class Adapterclassviewholder extends RecyclerView.ViewHolder {

        TextView textView1,textView2;

        public Adapterclassviewholder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.title);
            textView2 = itemView.findViewById(R.id.subtitle);

        }
    }
}


