package com.example.sound2.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.sound2.R;

import java.util.ArrayList;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHoderFavorite> {
    ArrayList<Favorite> list;
    Context context;
    MainActivity m;
    DAO_favorite favorite;

    public AdapterFavorite(ArrayList<Favorite> list, MainActivity m) {
        this.list = list;
        this.m = m;
    }

    @NonNull
    @Override
    public ViewHoderFavorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_favorite, parent, false);
        context = parent.getContext();
        return new ViewHoderFavorite(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoderFavorite holder, int position) {
        final  int index  =position;
        Favorite favoriteObj = list.get(index);
        holder.tvName.setText(favoriteObj.getName());
        Glide.with(holder.itemView).load(favoriteObj.getImage()).into(holder.imgAvata);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailItemVoiceActivity.class);
                intent.putExtra("id", favoriteObj.getId());
                intent.putExtra("name", favoriteObj.getName());
                intent.putExtra("sound", favoriteObj.getSound());
                intent.putExtra("image", favoriteObj.getImage());
                intent.putExtra("framentSound", "soundFavorite");
                m.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogDelete(favoriteObj, index);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0: list.size();
    }
    public void dialogDelete(Favorite obj, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa mục này không");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = favorite.deleteRow(obj);
                if (res > 0) {
                    list.remove(index);
                    notifyItemRemoved(index);
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

    public class ViewHoderFavorite extends RecyclerView.ViewHolder {
        private ImageView imgAvata;
        private TextView tvName;
        public ViewHoderFavorite(@NonNull View itemView) {
            super(itemView);
            imgAvata = (ImageView) itemView.findViewById(R.id.img_avata);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);

        }
    }

}
