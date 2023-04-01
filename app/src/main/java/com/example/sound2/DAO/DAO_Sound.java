package com.example.sound2.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sound2.Models.Sound;
import com.example.sound2.database.Database;

import java.util.ArrayList;

public class DAO_Sound {
    SQLiteDatabase database=null;
    Database db;
    static  final String DB_name ="hairCut_1.db" ;
    public DAO_Sound(Context context) {
        db = new Database(context);
        database = db.getWritableDatabase();

    }

    public void close() {
        db.close();
    }
    public ArrayList<Sound> getList(){
        ArrayList<Sound> list = new ArrayList<>();
        String select ="select * from HairCut";
        Cursor cursor = database.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Sound obj = new Sound();
                obj.setId(cursor.getInt(0));
                obj.setName(cursor.getString(1));
                obj.setSound(cursor.getString(2));
                obj.setImage(cursor.getString(3));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return  list;
    }

}
