package com.example.schoolproject.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolproject.R;
import com.example.schoolproject.model.Notice;

import java.util.ArrayList;

public class NoticeAdapters extends RecyclerView.Adapter<NoticeAdapters.myviewholder> {
    ArrayList<Notice> list;
    onNoticeListener noticeListener;

    public NoticeAdapters(ArrayList<Notice> list, onNoticeListener noticeListener) {
        this.list = list;
        this.noticeListener = noticeListener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_notice_layout, parent, false);
        return new myviewholder(view, noticeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.classnm.setText(list.get(position).getTarget_class());
        holder.dte.setText(list.get(position).getDate());
        holder.ntctitle.setText(list.get(position).getNotice_title());
        holder.ntcbdy.setText(list.get(position).getNotice_body());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onNoticeListener {
        void onClickItem(int position, View view);
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        onNoticeListener noticeListener;
        TextView classnm, dte, ntctitle, ntcbdy;
        Button learnmore;

        public myviewholder(@NonNull View itemView, onNoticeListener noticeListener) {
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
        public void onClick(View view) {
            noticeListener.onClickItem(getAdapterPosition(), view);
        }
    }
}
