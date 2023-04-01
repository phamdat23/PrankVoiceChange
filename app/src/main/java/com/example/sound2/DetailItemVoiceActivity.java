package com.example.sound2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.widget.ContentLoadingProgressBar;

import com.bumptech.glide.Glide;
import com.example.sound2.sevices.PlaySound;

public class DetailItemVoiceActivity extends AppCompatActivity {
    private ImageView imgBack;
    private ImageView imgAvata;
    private SwitchCompat switchLoop;
    private ImageView imgLoudspeaker;
    private ContentLoadingProgressBar progressMusic;
    private TextView tvTime;
    PlaySound playSound;
    boolean loop =false;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlaySound.LocaBinde locaBinde = (PlaySound.LocaBinde) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_voice);
        Intent intent = getIntent();
        playSound = new PlaySound(DetailItemVoiceActivity.this, DetailItemVoiceActivity.this);
        Intent startSevice = new Intent(DetailItemVoiceActivity.this, PlaySound.class);
        this.bindService(startSevice, connection, Context.BIND_AUTO_CREATE);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgAvata = (ImageView) findViewById(R.id.img_avata);
        switchLoop = (SwitchCompat) findViewById(R.id.switch_loop);
        imgLoudspeaker = (ImageView) findViewById(R.id.img_loudspeaker);


        tvTime = (TextView) findViewById(R.id.tv_time);

        progressMusic = (ContentLoadingProgressBar) findViewById(R.id.progress_music);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        Log.e("TAG", "onCreate: "+intent.getStringExtra("image"));
        Glide.with(this).load(intent.getStringExtra("image")).into(imgAvata);
        loop =switchLoop.isChecked();
        Log.e("Loop", "onCreate: "+loop);
        Log.e("TAG", "onCreate: đường dẫn voice"+intent.getStringExtra("sound") );
        playSound.startMusic(intent.getStringExtra("sound"));
        playSound.musicBar(progressMusic, tvTime);
        if(switchLoop.isChecked()){
            playSound.loop(loop);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loop= switchLoop.isChecked();
        playSound.loop(loop);
    }

    @Override
    protected void onPause() {
        super.onPause();
        playSound.Stop();
    }
}