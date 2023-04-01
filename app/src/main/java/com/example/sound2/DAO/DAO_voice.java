package com.example.sound2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sound2.Models.Voice;
import com.example.sound2.database.Database;

import java.util.ArrayList;

public class DAO_voice {
    SQLiteDatabase database;
    Database db ;
    public DAO_voice(Context context){
        db= new Database(context);
        database= db.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public  long insertRecod(Voice obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",obj.getName());
        contentValues.put("file", obj.getFile());
        contentValues.put("image",obj.getImage());
        return database.insert("voice", null, contentValues);
    }
    public ArrayList<Voice> getVoices(){
        ArrayList<Voice> list = new ArrayList<>();
        String select ="select * from voice";
        Cursor cursor = database.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Voice obj = new Voice();
                obj.setId(cursor.getInt(0));
                obj.setName(cursor.getString(1));
                obj.setFile(cursor.getString(2));
                obj.setImage(cursor.getString(3));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public int delete(Voice obj){
        return database.delete("voice", "id=?", new String[]{obj.getId()+""});
    }


}
