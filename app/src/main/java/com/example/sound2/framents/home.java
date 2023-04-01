package com.example.sound2.framents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.sound2.MainActivity;
import com.example.sound2.R;


public class home extends Fragment {
    private RelativeLayout btnSound;
    private RelativeLayout btnVoice;
    private RelativeLayout btnFavorite;
    MainActivity m ;

    public home(MainActivity m) {
        this.m = m;
    }

    public home() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        m.bottomMenu.getMenu().findItem(R.id.home).setChecked(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        m.bottomMenu.getMenu().findItem(R.id.home).setChecked(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnSound = (RelativeLayout) view.findViewById(R.id.btn_sound);
        btnVoice = (RelativeLayout) view.findViewById(R.id.btn_voice);
        btnFavorite = (RelativeLayout) view.findViewById(R.id.btn_favorite);
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.SetFrament(new sound(m));
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.SetFrament(new favorite(m));
            }
        });
        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.SetFrament(new voice(m));
            }
        });

    }
}