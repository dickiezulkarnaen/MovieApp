package com.example.dickiez.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Dickiez on 12/3/2017.
 */

public interface ApiInterface {
    public static final String DB_API = "81728309a12b337c7334a06681733deb";

    @GET("popular?api_key="+DB_API)
    Call<Movie> getPopular();
    @GET("top_rated?api_key="+DB_API)
    Call<Movie> getTopRated();
    @GET("now_playing?api_key="+DB_API)
    Call<Movie> getPlaying();
    @GET("upcoming?api_key="+DB_API)
    Call<Movie> getUpcoming();

    //BASE_URL = "http://api.themoviedb.org/3/movie/";
    //https://api.themoviedb.org/3/movie/337167/videos?api_key=81728309a12b337c7334a06681733deb

    @GET("{movie_id}/videos?api_key="+DB_API)
    Call<Video> getLink(@Path("movie_id") int movie_id);

}
