package com.example.dickiez.movieapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dickiez on 12/6/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    List<Result> results;

    public MovieAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        Picasso.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w342"+results.get(position).getPosterPath())
                .into(holder.poster);

        holder.title.setText(results.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result data = results.get(position);
                Intent i = new Intent(holder.itemView.getContext(), Detail. class);
                i.putExtra("movie", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setData(List<Result> results) {
        this.results = results;
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;
        public MovieHolder(View itemView) {
            super(itemView);

            poster = (ImageView)itemView.findViewById(R.id.poster);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
