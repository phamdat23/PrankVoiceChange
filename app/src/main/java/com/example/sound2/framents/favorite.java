package com.example.sound2.framents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sound2.Adapters.AdapterFavorite;
import com.example.sound2.DAO.DAO_favorite;
import com.example.sound2.MainActivity;
import com.example.sound2.Models.Favorite;
import com.example.sound2.R;

import java.util.ArrayList;


public class favorite extends Fragment {

    MainActivity m;
    DAO_favorite dao_favorite;
    AdapterFavorite adapterFavorite;
    ArrayList<Favorite> list;
    public favorite() {
    }

    public favorite(MainActivity m) {
        this.m = m;
    }
    private RecyclerView rcvListFavorite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        m.bottomMenu.getMenu().findItem(R.id.favorite).setChecked(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        m.bottomMenu.getMenu().findItem(R.id.favorite).setChecked(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvListFavorite = (RecyclerView) view.findViewById(R.id.rcv_listFavorite);
        dao_favorite = new DAO_favorite(getContext());
        list = dao_favorite.getListFavorite();
        adapterFavorite = new AdapterFavorite(list, m);
        rcvListFavorite.setAdapter(adapterFavorite);


    }
}