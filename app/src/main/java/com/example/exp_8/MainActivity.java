package com.example.exp_8;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
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
        mediaPlayer=null;
        }

    public void Music(View view) {

        switch(view.getId()) {
            case R.id.button1:
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.sample_music);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopMusic();
                        }
                    });
                }
                mediaPlayer.start();
                break;

            case R.id.button2:
                if (mediaPlayer != null ) {
                    mediaPlayer.pause();
                }
                break;

            case R.id.button3:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    stopMusic();
                }
                break;
        }
    }
    private void stopMusic() {
        mediaPlayer.release();
        mediaPlayer=null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }
}