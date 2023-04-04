package com.example.sound2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MagicActivity extends AppCompatActivity {
    private ImageView imgBack;
    private TextView tvName;
    private ImageView imgRadio;
    private ImageView imgAvata2;
    private LinearLayout btnMagic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic);
        Intent getData = getIntent();
        imgBack = (ImageView) findViewById(R.id.img_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        imgRadio = (ImageView) findViewById(R.id.img_radio);
        imgAvata2 = (ImageView) findViewById(R.id.img_avata2);
        btnMagic = (LinearLayout) findViewById(R.id.btn_magic);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MagicActivity.this, DetailItemVoiceActivity.class);
                intent.putExtra("id",getData.getStringExtra("id"));
                intent.putExtra("name", getData.getStringExtra("name"));
                intent.putExtra("sound", getData.getStringExtra("voice"));
                intent.putExtra("image", getData.getStringExtra("image"));
                startActivity(intent);
            }
        });
        Glide.with(this).load(getData.getStringExtra("image")).into(imgAvata2);
        imgAvata2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MagicActivity.this, MainActivity.class);
                intent.putExtra("nameFrament","voice");
                startActivity(intent);
            }
        });
    }
}