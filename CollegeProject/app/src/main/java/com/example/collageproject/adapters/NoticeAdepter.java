package com.example.collageproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collageproject.R;
import com.example.collageproject.models.Notice;

import java.util.ArrayList;

public class NoticeAdepter extends RecyclerView.Adapter<NoticeAdepter.MyViewHolder>{

    ArrayList<Notice> list;
    onNoticeListener noticeListener;


    public NoticeAdepter(ArrayList<Notice> list, onNoticeListener noticeListener) {
        this.list = list;
        this.noticeListener = noticeListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_notice_layout, parent, false);
        return new MyViewHolder(view, noticeListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.classnm.setText(list.get(position).getTarget_class());
        holder.dte.setText(list.get(position).getDate());
        holder.ntctitle.setText(list.get(position).getNotice_title());
        holder.ntcbdy.setText(list.get(position).getNotice_body());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        onNoticeListener noticeListener;
        TextView classnm, dte, ntctitle, ntcbdy;
        Button learnmore;

        public MyViewHolder(@NonNull View itemView,onNoticeListener noticeListener) {
            super(itemView);

            this.noticeListener = noticeListener;

            classnm = itemView.findViewById(R.id.targetclass);
            dte = itemView.findViewById(R.id.date);
            ntctitle = itemView.findViewById(R.id.noticetitle);
            ntcbdy = itemView.findViewById(R.id.noticebody);
            learnmore = itemView.findViewById(R.id.learn);

            learnmore.setOnClickListener(this);
        }




        @Override
        public void onClick(View v) {
            noticeListener.onClickItem(getAdapterPosition(), v);

        }


    }

    public interface onNoticeListener {
        void onClickItem(int position, View view);
    }
}
