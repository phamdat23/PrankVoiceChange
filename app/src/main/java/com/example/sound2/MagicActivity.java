package com.example.sound2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
                startActivity(intent);
            }
        });
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