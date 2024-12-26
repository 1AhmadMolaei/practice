package com.example.musicplayer;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;


import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private final Handler handler = new Handler();
    private ImageButton playButton;
    private ImageButton pauseButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mediaPlayer = MediaPlayer.create(this,R.raw.nuje);
        mediaPlayer.setLooping(true);
        playButton = findViewById(R.id.playBtn);
        pauseButton = findViewById(R.id.pauseBtn);

        // get song image
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(this, Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.nuje));
        byte[] songImage = retriever.getEmbeddedPicture();
        ImageView songCover = findViewById(R.id.songCover);
        if(songImage != null) songCover.setImageBitmap(BitmapFactory.decodeByteArray(songImage,0,songImage.length));

        // sync seekBar with mediaPlayer
        seekBar = findViewById(R.id.songTime);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) mediaPlayer.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView currentTime = findViewById(R.id.currentTime);
                currentTime.setText(formatTime(mediaPlayer.getCurrentPosition()));
            }
        });
        seekBar.setMax(mediaPlayer.getDuration());
        TextView songMax = findViewById(R.id.songLength);
        songMax.setText(formatTime(mediaPlayer.getDuration()));
    }
    // onClick functions for pause and play buttons
    public void onPauseClick(View v){
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        mediaPlayer.pause();
    }
    public void onPlayClick(View v){
        playButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.VISIBLE);
        mediaPlayer.start();
        updateSeekBar();
    }
    public void onNextClick(View v){


    }

    public void onPervClick(View v){


    }
    // handle mute button click

    private void updateSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        TextView currentTime = findViewById(R.id.currentTime);
        currentTime.setText(formatTime(mediaPlayer.getCurrentPosition()));
        if(mediaPlayer.isPlaying()) {
            handler.postDelayed(this::updateSeekBar,1000);
        }
    }
    // format time in milliseconds to mm:ss
    @SuppressLint("DefaultLocale")
    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}