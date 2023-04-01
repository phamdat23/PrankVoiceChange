package com.example.sound2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.sound2.DAO.DAO_voice;
import com.example.sound2.Models.Voice;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class RecordActivity extends AppCompatActivity {
    private LinearLayout lineTitle;
    private ImageView imgBack;
    private TextView tvName;
    private ImageView imgRadio;
    private TextView tvTime;
    private LinearLayout btnVoice;
    private ImageView imgMagic;
    String outputFile;
    DAO_voice dao_voice;
    Voice obj = new Voice();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        dao_voice = new DAO_voice(RecordActivity.this);
        lineTitle = (LinearLayout) findViewById(R.id.line_title);
        imgBack = (ImageView) findViewById(R.id.img_back);
        tvName = (TextView) findViewById(R.id.tv_name);
        imgRadio = (ImageView) findViewById(R.id.img_radio);
        tvTime = (TextView) findViewById(R.id.tv_time);
        btnVoice = (LinearLayout) findViewById(R.id.btn_voice);
        imgMagic = (ImageView) findViewById(R.id.img_magic);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imgMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordActivity.this, MagicActivity.class);
                intent.putExtra("name",obj.getName());
                intent.putExtra("voice",obj.getFile());
                intent.putExtra("image",obj.getImage());
                startActivity(intent);
            }
        });
        MediaRecorder recorder = new MediaRecorder();
        btnVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("TAG", "onTouch:  chạm view");
                        // Người dùng bắt đầu chạm vào ImageView
                        if (ActivityCompat.checkSelfPermission(RecordActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermission();
                        } else {
                        }
                        Log.d("TAG", "onTouch: bắt đầu record ");
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/recording.mp3";
                        Log.e("TAG", "onTouch:file "+outputFile );
                        recorder.setOutputFile(outputFile);
                        try {
                            recorder.prepare();
                        } catch (IOException e) {
                            Log.e("Error", "onTouch: " + e);
                        }
                        recorder.start();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Người dùng di chuyển tay trên ImageView
                        break;
                    case MotionEvent.ACTION_UP:
                        // Người dùng nhấc tay ra khỏi ImageView
                        Log.d("TAG", "onTouch: Không chạm mữa");
                        if(outputFile!=null){
                            recorder.stop();
                            recorder.release();
                            dialogSave(RecordActivity.this);
                            Log.d("TAG", "onTouch: kết thúc record");
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }

    public void dialogSave(Context context) {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_recording);

        TextInputLayout inputName = (TextInputLayout) dialog.findViewById(R.id.input_name);
        MaterialButton  btnSave = (MaterialButton) dialog.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getEditText().getText().toString();
                obj.setName(name);
                obj.setFile(outputFile);
                obj.setImage("https://cdn.iconscout.com/icon/free/png-256/voice-45-470369.png");
                long res = dao_voice.insertRecod(obj);
                if(res>0){
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void requestPermission() {
        ActivityCompat.requestPermissions(RecordActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 999);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 999 & grantResults[0] == 0) {
            // đồng ý

        } else {
            // không đồng ý
            Toast.makeText(RecordActivity.this, "Do bạn không đồng ý !!!", Toast.LENGTH_SHORT).show();
        }


    }
}