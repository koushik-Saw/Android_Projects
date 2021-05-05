package com.example.collageproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collageproject.R;
import com.example.collageproject.models.GalleryImageUpload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdepter extends RecyclerView.Adapter<GalleryAdepter.MyViewHolder> {

    private Context context;
    private List<GalleryImageUpload> galleryImageUploadList;
    onGalleryListener galleryListener;

    public GalleryAdepter(Context context, List<GalleryImageUpload> galleryImageUploadList, onGalleryListener galleryListener) {
        this.context = context;
        this.galleryImageUploadList = galleryImageUploadList;
        this.galleryListener = galleryListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.gellary_item_layout,parent,false);
        return new MyViewHolder(view,galleryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GalleryImageUpload galleryImageUpload = galleryImageUploadList.get(position);
        Picasso.get()
                .load(galleryImageUpload.getGalleryImageUri())
                .placeholder(R.drawable.lodding)
                .into(holder.galleryImage);

    }

    @Override
    public int getItemCount() {
        return galleryImageUploadList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView galleryImage;
        onGalleryListener onGalleryListener;

        public MyViewHolder(@NonNull View itemView,onGalleryListener galleryListener) {
            super(itemView);
            this.onGalleryListener = galleryListener;
            galleryImage = itemView.findViewById(R.id.galleryImageRowIV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onGalleryListener.onClickItem(getAdapterPosition(),v);
        }
    }

    public interface onGalleryListener{
        void onClickItem(int position,View v);
    }
}
