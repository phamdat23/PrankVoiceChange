package com.example.sound2.framents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sound2.Adapters.AdapterSound;
import com.example.sound2.DAO.DAO_Sound;
import com.example.sound2.MainActivity;
import com.example.sound2.Models.Sound;
import com.example.sound2.R;

import java.util.ArrayList;


public class sound extends Fragment {

    MainActivity m;
    private RecyclerView rcvListSound;

    public sound(MainActivity m) {
        this.m = m;
    }

    public sound() {
    }
    DAO_Sound dao_sound;
    ArrayList<Sound> listSound;
    AdapterSound adapterSound;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sound, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        m.bottomMenu.getMenu().findItem(R.id.sound).setChecked(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        m.bottomMenu.getMenu().findItem(R.id.sound).setChecked(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListSound = (RecyclerView) view.findViewById(R.id.rcv_listSound);
        dao_sound = new DAO_Sound(getContext());
        listSound = dao_sound.getList();
        adapterSound = new AdapterSound(listSound, m);
        rcvListSound.setAdapter(adapterSound);


    }
}