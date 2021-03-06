package com.example.dickiez.movieapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.GsonBuilder;

public class Streaming extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    TextView mDisplayText;
    VideoResult videoResult;
    public static final String API_KEY = "AIzaSyDvOGU3w3pbrdnCuBVPIv5ep7xWJXKH3Ws";
    private static String video_key = "";
    private static final int RECOVERY_DIALOG_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streaming);

        YouTubePlayerView  youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeplayer);
        youTubePlayerView.initialize(API_KEY,this);

        mDisplayText = (TextView) findViewById(R.id.tv_display);

        Bundle data = getIntent().getExtras();
        String data_video = data.getString("movie");
        mDisplayText.setText(data_video);
        video_key = data_video;
        }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(!wasRestored) {
            player.loadVideo(video_key);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("Ada Sebuah Kesalahan Yang Menimpa (%1$s)", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            getYoutubePlayerProvider().initialize(API_KEY,this);
        }
    }

    private YouTubePlayer.Provider getYoutubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtubeplayer);
    }
}