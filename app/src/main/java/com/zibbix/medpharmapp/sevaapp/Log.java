package com.zibbix.medpharmapp.sevaapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Log extends AppCompatActivity {
    private VideoView mVideoView;
    private Button obtn, fbtn, pbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        mVideoView = (VideoView) findViewById(R.id.bgVideoView);
        obtn = findViewById(R.id.otherAccButton);
        fbtn = findViewById(R.id.fbLoginButton);
        pbtn = findViewById(R.id.wechatLoginButton);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.police);
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        obtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}

