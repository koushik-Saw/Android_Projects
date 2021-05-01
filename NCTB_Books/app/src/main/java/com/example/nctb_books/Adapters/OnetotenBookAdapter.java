package com.example.nctb_books.Adapters;

import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nctb_books.Model.ClassBookList;
import com.example.nctb_books.R;

import java.util.ArrayList;

public class OnetotenBookAdapter extends RecyclerView.Adapter<OnetotenBookAdapter.myviewholder> implements Filterable {

    ArrayList<ClassBookList> list;
    ArrayList<ClassBookList> listFiltered;
    onBooklinstener onBooklinstener;

    public OnetotenBookAdapter(ArrayList<ClassBookList> list, onBooklinstener onBooklinstener) {
        this.list = list;
        this.listFiltered = list;
        this.onBooklinstener = onBooklinstener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_onetoten_booklist, parent, false);
        return new myviewholder(view, onBooklinstener);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.booknames.setText(listFiltered.get(position).getClassNo());
        holder.booklinks.setText(listFiltered.get(position).getBook_Link());
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
                    ArrayList<ClassBookList> filteredList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String string = list.get(i).getClassNo();
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
                listFiltered = (ArrayList<ClassBookList>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView booknames,booklinks;
        Button openbook;
        onBooklinstener onBooklinstener;

        public myviewholder(@NonNull View itemView, onBooklinstener onBooklinstener) {
            super(itemView);
            this.onBooklinstener = onBooklinstener;
            booknames = itemView.findViewById(R.id.Booknames);
            booklinks = itemView.findViewById(R.id.bookLink);
            openbook = itemView.findViewById(R.id.openLink);
            openbook.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBooklinstener.onClickItem(getAdapterPosition());
        }

    }

    public interface onBooklinstener {
        void onClickItem(int position);
    }
}
