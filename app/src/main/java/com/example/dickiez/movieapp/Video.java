
package com.example.dickiez.movieapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    @Expose
    private Integer videoid;
    @SerializedName("results")
    @Expose
    private List<VideoResult> videoresults = null;

    public Integer getVideoId() {
        return videoid;
    }

    public void setVideoId(Integer id) {
        this.videoid = videoid;
    }

    public List<VideoResult> getVideoResults() {
        return videoresults;
    }

    public void setVideoResults(List<VideoResult> videoresults) {
        this.videoresults = videoresults;
    }

}
