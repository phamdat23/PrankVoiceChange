package com.example.sound2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sound2.Models.Favorite;
import com.example.sound2.database.Database;

import java.util.ArrayList;

public class DAO_favorite {
    SQLiteDatabase database;
    Database db;
    public  DAO_favorite(Context context){
        db = new Database(context);
        database = db.getWritableDatabase();
    }
    public  void close(){
        db.close();
    }
    public ArrayList<Favorite> getListFavorite(){
        ArrayList<Favorite> list = new ArrayList<>();
        String select = "select * from favorite";
        Cursor cursor = database.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Favorite obj = new Favorite();
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
    public int insertRow (Favorite obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", obj.getName());
        contentValues.put("sound",obj.getSound());
        contentValues.put("image",obj.getImage());
        return (int) database.insert("favorite", null, contentValues);
    }
    public int  deleteRow (Favorite obj){
        return database.delete("favorite","id=?", new String[]{obj.getId()+""});
    }

}
