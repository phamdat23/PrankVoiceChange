package com.example.sound2.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sound2.DAO.DAO_favorite;
import com.example.sound2.DetailItemVoiceActivity;
import com.example.sound2.MainActivity;
import com.example.sound2.Models.Favorite;
import com.example.sound2.Models.Sound;
import com.example.sound2.R;

import java.util.ArrayList;

public class AdapterSound extends RecyclerView.Adapter<AdapterSound.ViewHoderItemSound> {

    ArrayList<Sound> list;
    DAO_favorite favorite;

    public AdapterSound(ArrayList<Sound> list, MainActivity m) {
        this.list = list;
        this.m = m;
    }

    Context context;
    MainActivity m;

    @NonNull
    @Override
    public ViewHoderItemSound onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_list_sound, parent, false);
        context = parent.getContext();
        favorite = new DAO_favorite(context);
        return new ViewHoderItemSound(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderItemSound holder, int position) {
        final int index = position;
        Sound objSound = list.get(index);
        holder.tvName.setText(objSound.getName());
        holder.imgAvata.setImageURI(Uri.parse(objSound.getImage()));
        Glide.with(holder.itemView).load(objSound.getImage()).into(holder.imgAvata);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailItemVoiceActivity.class);
                intent.putExtra("id", objSound.getId());
                intent.putExtra("name", objSound.getName());
                intent.putExtra("sound", objSound.getSound());
                intent.putExtra("image", objSound.getImage());
                m.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Favorite obj = new Favorite(objSound.getName(), objSound.getSound(), objSound.getImage());
                dialog(obj);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void dialog(Favorite obj) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thêm mục");
        builder.setMessage("Bạn có muốn thêm sound này vào mục favorite không ");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = favorite.insertRow(obj);
                if (res > 0) {
                    notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public class ViewHoderItemSound extends RecyclerView.ViewHolder {
        private ImageView imgAvata;
        private TextView tvName;

        public ViewHoderItemSound(@NonNull View itemView) {
            super(itemView);
            imgAvata = (ImageView) itemView.findViewById(R.id.img_avata);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);

        }
    }
}
