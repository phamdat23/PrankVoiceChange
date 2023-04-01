package com.example.sound2.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sound2.DAO.DAO_voice;
import com.example.sound2.DetailItemVoiceActivity;
import com.example.sound2.MainActivity;
import com.example.sound2.Models.Voice;
import com.example.sound2.R;

import java.util.ArrayList;

public class AdapterVoice extends RecyclerView.Adapter<AdapterVoice.ViewHodeVoice> {
    ArrayList<Voice> list;
    Context context;

    public AdapterVoice(ArrayList<Voice> list, MainActivity m) {
        this.list = list;
        this.m = m;
    }

    MainActivity m;
    DAO_voice dao_voice;
    @NonNull
    @Override
    public ViewHodeVoice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_voice, parent, false);
        context = parent.getContext();
        dao_voice = new DAO_voice(context);
        return new ViewHodeVoice(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodeVoice holder, int position) {
        final  int index = position;
        Voice obj = list.get(index);
        holder.tvName.setText(obj.getName());
        Glide.with(holder.itemView).load(obj.getImage()).into(holder.imgRecord);
        Log.d("TAG", "onBindViewHolder: "+obj.getFile());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailItemVoiceActivity.class);
                intent.putExtra("id", obj.getId());
                intent.putExtra("name", obj.getName());
                intent.putExtra("sound", obj.getFile());
                intent.putExtra("image", obj.getImage());
               intent.putExtra("framentSound", "sound");
                m.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog(obj, index);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list ==null?0: list.size();
    }
    public void dialog(Voice obj, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thêm mục");
        builder.setMessage("Bạn có muốn thêm sound này vào mục favorite không ");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_voice.delete(obj);
                if (res > 0) {
                    notifyItemRemoved(index);
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
    public class ViewHodeVoice extends RecyclerView.ViewHolder {
        private ImageView imgRecord;
        private TextView tvName;



        public ViewHodeVoice(@NonNull View itemView) {
            super(itemView);
            imgRecord = (ImageView) itemView.findViewById(R.id.img_record);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
