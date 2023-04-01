package com.example.sound2.framents;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sound2.Adapters.AdapterVoice;
import com.example.sound2.DAO.DAO_voice;
import com.example.sound2.MainActivity;
import com.example.sound2.Models.Voice;
import com.example.sound2.R;
import com.example.sound2.RecordActivity;

import java.util.ArrayList;


public class voice extends Fragment {


    public voice() {
    }
    private ConstraintLayout cTitle;
    private TextView tvTitle1;
    private ImageView imgHat;
    private RecyclerView rcvListSound;
    private LinearLayout btnVoiceSearch;
    MainActivity m;
    ArrayList<Voice> listVoice;
    DAO_voice dao_voice;
    AdapterVoice adapterVoice;

    public voice(MainActivity m) {
        this.m = m;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voice, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        m.bottomMenu.getMenu().findItem(R.id.voice).setChecked(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        m.bottomMenu.getMenu().findItem(R.id.voice).setChecked(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cTitle = (ConstraintLayout) view.findViewById(R.id.c_title);
        tvTitle1 = (TextView) view.findViewById(R.id.tv_title1);
        imgHat = (ImageView) view.findViewById(R.id.img_hat);
        rcvListSound = (RecyclerView) view.findViewById(R.id.rcv_listSound);
        btnVoiceSearch = (LinearLayout) view.findViewById(R.id.btn_voice_search);
        btnVoiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecordActivity.class);
                startActivity(intent);
            }
        });
        dao_voice = new DAO_voice(getContext());
        listVoice = dao_voice.getVoices();
        adapterVoice = new AdapterVoice(listVoice, m);
        rcvListSound.setAdapter(adapterVoice);

    }
}