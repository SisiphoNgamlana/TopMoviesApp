package com.example.topmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topmovies.mvp.TopMoviesViewModel;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.TopMoviesViewHolder> {

    private final List<TopMoviesViewModel> list;

    public ListAdapter(List<TopMoviesViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TopMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new TopMoviesViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMoviesViewHolder holder, int position) {
        holder.itemName.setText(list.get(position).getName());
        holder.countryName.setText(list.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TopMoviesViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView countryName;

        public TopMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.textView_fragmentlist_task_name);
            countryName = itemView.findViewById(R.id.textView_fragmentlist_country_name);
        }
    }
}
