package com.example.sound2.sevices;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.sound2.DetailItemVoiceActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PlaySound extends Service {
    DetailItemVoiceActivity dm;
    MediaPlayer mediaPlayer = null;
    Context context;

    public PlaySound(Context context, DetailItemVoiceActivity dm) {
        this.context = context;
        this.dm = dm;
    }

    public class LocaBinde extends Binder {
        IBinder iBinder = new LocaBinde();

        LocaBinde getLocabind() {
            return LocaBinde.this;
        }
    }

    public void startMusic(String link) {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, Uri.parse(link));
            mediaPlayer.start();
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            });
        } else {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            } else {
                try {
                    if (mediaPlayer.isLooping()) {
                        mediaPlayer.prepare();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }

        }


    }

    public void playMusic() {
        if (mediaPlayer != null) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            } else {
                PauseMusic();
            }
        }
    }

    public void loop(boolean a) {

        if (a == true) {
            mediaPlayer.setLooping(true);
        } else {
            mediaPlayer.setLooping(false);
        }


    }
    public void Loudspeaker(){

    }


    public void PauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void Stop() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    private String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void musicBar(ProgressBar progressBar, TextView time) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int totalTime = mediaPlayer.getDuration();
                    int curentTime = mediaPlayer.getCurrentPosition();
                    progressBar.setMax(totalTime);
                    progressBar.setProgress(curentTime);
                    progressBar.postDelayed(this, 100);
                    time.setText(formatDuration(curentTime));

                }
            }
        };
        runnable.run();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
