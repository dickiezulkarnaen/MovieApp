package com.example.dickiez.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {
    RecyclerView mView;
    MovieAdapter adapter;
    List<Result> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = (RecyclerView)findViewById(R.id.recycler);
        mView.setLayoutManager(new GridLayoutManager(Main.this, 2));

        movieLoad("popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.popular) {
            movieLoad("popular");
        }
        else if (id == R.id.toprated) {
            movieLoad("toprated");
        }
        else if (id == R.id.playing) {
            movieLoad("playing");
        }
        else if (id == R.id.upcoming) {
            movieLoad("upcoming");
        }
        return true;
    }

    private void movieLoad(String value) {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Movie> call =null;
        if (value.equals("popular")) {
            call = api.getPopular();
        }
        else if (value.equals("toprated")) {
            call = api.getTopRated();
        }
        else if (value.equals("playing")) {
            call = api.getPlaying();
        }
        else if (value.equals("upcoming")) {
            call = api.getUpcoming();
        }
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                adapter = new MovieAdapter(results);
                adapter.setData(movie.getResults());
                mView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}
