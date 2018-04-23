package com.example.dickiez.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {
    Integer video_id;
    String video_key;
    ImageView backdrop, poster, adult, pg;
    TextView title, rating, release, overview, orilang, genre, txt_video_id;
    Video video;
    Result result;
    List<VideoResult> videoResults;
    Button bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        backdrop = (ImageView)findViewById(R.id.detail_backdrop);
        poster = (ImageView)findViewById(R.id.detail_poster);
        adult = (ImageView)findViewById(R.id.adult);
        pg = (ImageView)findViewById(R.id.pg);
        title = (TextView)findViewById(R.id.detail_title);
        rating = (TextView)findViewById(R.id.detail_rating);
        release = (TextView)findViewById(R.id.detail_release);
        overview = (TextView)findViewById(R.id.detail_overview);
        orilang = (TextView)findViewById(R.id.detail_lang);
        genre = (TextView)findViewById(R.id.detail_genre);
        bookmark = (Button)findViewById(R.id.btn_bookmark);
        txt_video_id = (TextView)findViewById(R.id.vid_id);


        result = new GsonBuilder().create()
                .fromJson(getIntent().getStringExtra("movie"), Result.class);

        Picasso.with(Detail.this)
                .load("http://image.tmdb.org/t/p/w500"+result.getBackdropPath())
                .into(backdrop);

        Picasso.with(Detail.this)
                .load("http://image.tmdb.org/t/p/w185"+result.getPosterPath())
                .into(poster);

        title.setText(result.getTitle());
        rating.setText(Double.toString(result.getVoteAverage()));
        release.setText(result.getReleaseDate());
        overview.setText(result.getOverview());
        orilang.setText(result.getOriginalLanguage());
        video_id = result.getId();
        youtubeLoad();

        //id.setText(Integer.toString(result.getId()));
            adult.setVisibility(View.VISIBLE);

            if (result.getAdult() == true) {
            pg.setVisibility(View.INVISIBLE);
        } else if (result.getAdult() == false) {
            pg.setVisibility(View.VISIBLE);
            adult.setVisibility(View.INVISIBLE);
        }


        backdrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String  data = video_key;
                    Intent i = new Intent(Detail.this, Streaming. class);
                    i.putExtra("key", data);
                    backdrop.getContext().startActivity(i);

                }
        });

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bookmark Successfull", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void youtubeLoad() {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<Video> call = api.getLink(video_id);
        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                videoResults = response.body().getVideoResults();
                video_key = videoResults.get(0).getKey();
                txt_video_id.setText(video_key);
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {

            }
        });

    }
}

