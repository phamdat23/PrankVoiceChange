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
import android.os.Handler;
import android.util.Log;
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
import java.util.concurrent.TimeUnit;

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
    private Handler handler = new Handler();
    private long timeElapsed;
    boolean checkRecrod = true;
    private TextView tvTextMsg;
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


        tvTextMsg = (TextView) findViewById(R.id.tv_text_msg);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if (ActivityCompat.checkSelfPermission(RecordActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        MediaRecorder recorder = new MediaRecorder();
        Log.d("TAG", "onCreate: "+recorder);

//        btnVoice.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d("TAG", "onTouch:  chạm view");
//                        // Người dùng bắt đầu chạm vào ImageView
//
//                        Log.d("TAG", "onTouch: bắt đầu record ");
//                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/recording" + System.currentTimeMillis() + ".mp3";
//                        Log.e("TAG", "onTouch:file " + outputFile);
//                        recorder.setOutputFile(outputFile);
//                        try {
//                            recorder.prepare();
//                        } catch (IOException e) {
//                            Log.e("Error", "onTouch: " + e);
//                        }
//                        handler.postDelayed(updateTimerRunnable, 1000);
//                        recorder.start();
//
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        // Người dùng di chuyển tay trên ImageView
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        // Người dùng nhấc tay ra khỏi ImageView
//                        Log.d("TAG", "onTouch: Không chạm mữa");
//                        try {
//                            recorder.stop();
//                            recorder.release();
//                            recorder.reset();
//                            dialogSave(RecordActivity.this);
//                            Log.d("TAG", "onTouch: kết thúc record");
//                            handler.removeCallbacks(updateTimerRunnable);
//                        }catch (Exception e){
//                            Log.e("TAG", "onTouch: Error "+e );
//                        }
//
//                        break;
//                    default:
//                        return false;
//                }
//                return true;
//            }
//        });
        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    if(checkRecrod==true){
                        checkRecrod=false;
                        Log.d("TAG", "onTouch: bắt đầu record ");

                        if(timeElapsed==0){
                            Log.d("TAG", "onTouch: bắt đầu record ");
                            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            String filename = String.valueOf(Environment.getExternalStorageDirectory());
                            Log.d("TAG", "onClick: "+filename);
                            outputFile =getExternalCacheDir().getAbsolutePath()+"/recording" + System.currentTimeMillis() + ".mp3";
                            Log.e("TAG", "onTouch:file " + outputFile);
                            recorder.setOutputFile(outputFile);
                            try {
                                recorder.prepare();
                            } catch (IOException e) {
                                Log.e("Error", "onTouch: " + e);
                            }
                            handler.postDelayed(updateTimerRunnable, 1000);
                            recorder.start();
                        }else{
                            handler.postDelayed(updateTimerRunnable, 1000);
                            recorder.resume();


                        }
                        tvTextMsg.setText("Ấn để tạm dừng ghi âm");
                        Toast.makeText(RecordActivity.this, "Đang recroed" , Toast.LENGTH_SHORT).show();
                    }else if(checkRecrod==false){
                        checkRecrod=true;
                        tvTextMsg.setText("Ấn để tiếp tục ghi âm");
                        Log.d("TAG", "onClick:tạm dùng recored ");
                        recorder.pause();
                        handler.removeCallbacks(updateTimerRunnable);
                        Toast.makeText(RecordActivity.this, " recroed pause" , Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onTouch: pause record ");
                    }




            }
        });
        imgMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timeElapsed!=0){
                    if(recorder!=null){
                        recorder.stop();
                        recorder.release();
                        dialogSave(RecordActivity.this);
                        timeElapsed=0;
                        Log.d("TAG", "onTouch: kết thúc record");
                        handler.removeCallbacks(updateTimerRunnable);
                    }

                }




            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRestart();
        timeElapsed=0;
    }

    private String formatDuration(long duration) {
        long minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS)
                - minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);

        return String.format("%02d:%02d", minutes, seconds);
    }

    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            timeElapsed += 1000;
            // Update UI with elapsed time
            tvTime.setText(formatDuration(timeElapsed));
            handler.postDelayed(this, 1000);
        }
    };

    public void dialogSave(Context context) {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_recording);

        TextInputLayout inputName = (TextInputLayout) dialog.findViewById(R.id.input_name);
        MaterialButton btnSave = (MaterialButton) dialog.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getEditText().getText().toString();
                obj.setName(name);
                obj.setFile(outputFile);
                obj.setImage("https://cdn.iconscout.com/icon/free/png-256/voice-45-470369.png");
                long res = dao_voice.insertRecod(obj);
                if (res > 0){
                    Intent intent = new Intent(RecordActivity.this, MagicActivity.class);
                    intent.putExtra("id", obj.getId());
                    intent.putExtra("name", obj.getName());
                    intent.putExtra("voice", obj.getFile());
                    intent.putExtra("image", obj.getImage());
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }


    public void requestPermission() {
        ActivityCompat.requestPermissions(RecordActivity.this, new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 999);
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