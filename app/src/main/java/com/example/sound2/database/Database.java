package com.example.sound2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Database extends SQLiteOpenHelper {
    static final String db_NAME = "sound.db";
    static final int VERSION = 1;
    public static  final  String FILE_Name ="hairCut_1.db";
    static String DB_PATH ="";
    private final Context context;


    public Database(Context context) {
        super(context, db_NAME, null, VERSION);
        this.context = context;
        DB_PATH= context.getDatabasePath(FILE_Name).toString();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTb =" CREATE TABLE hairCut (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name STRING NOT NULL, sound STRING NOT NULL, image STRING NOT NULL)";
        sqLiteDatabase.execSQL(createTb);
        String insert1 ="INSERT INTO hairCut VALUES(1,'air','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_1.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert1);
        String insert2 ="INSERT INTO hairCut VALUES(2,'air1','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_2.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert2);
        String insert3 ="INSERT INTO hairCut VALUES(3,'air2','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_3.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert3);
        String insert4 ="INSERT INTO hairCut VALUES(4,'air3','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_4.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert4);
        String insert5 ="INSERT INTO hairCut VALUES(5,'air4','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_5.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert5);
        String insert6 ="INSERT INTO hairCut VALUES(6,'air5','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_6.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert6);
        String insert7 ="INSERT INTO hairCut VALUES(7,'air6','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_7.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert7);
        String insert8 ="INSERT INTO hairCut VALUES(8,'air7','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_8.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert8);
        String insert9 ="INSERT INTO hairCut VALUES(9,'air8','https://github.com/quockhanh09/data-haircut-prank/raw/main/air/funny_airhorn_9.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/air/ic_app_air_horn_sound.webp')";
        sqLiteDatabase.execSQL(insert9);
        String insert10 ="INSERT INTO hairCut VALUES(10,'baby-sneeze','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_1.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby-sneeze.png')";
        sqLiteDatabase.execSQL(insert10);
        String insert11 ="INSERT INTO hairCut VALUES(11,'baby-sneeze2','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_2.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby-sneeze.png')";
        sqLiteDatabase.execSQL(insert11);
        String insert12 ="INSERT INTO hairCut VALUES(12,'baby-sneeze3','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_3.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby-sneeze.png')";
        sqLiteDatabase.execSQL(insert12);
        String insert13 ="INSERT INTO hairCut VALUES(13,'bear_roar_','https://github.com/quockhanh09/data-haircut-prank/raw/main/bearroarsound/bear_roar_sound.wav', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/bearroarsound/img_bear_roar_sound_preview.webp')";
        sqLiteDatabase.execSQL(insert13);
        String insert14 ="INSERT INTO hairCut VALUES(14,'breaking','https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking_1.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking.png')";
        sqLiteDatabase.execSQL(insert14);
        String insert15 ="INSERT INTO hairCut VALUES(15,'breaking2','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_2.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking.png')";
        sqLiteDatabase.execSQL(insert15);
        String insert16 ="INSERT INTO hairCut VALUES(16,'breaking3','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_3.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking.png')";
        sqLiteDatabase.execSQL(insert16);
        String insert17 ="INSERT INTO hairCut VALUES(17,'breaking4','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_4.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking.png')";
        sqLiteDatabase.execSQL(insert17);
        String insert18 ="INSERT INTO hairCut VALUES(18,'breaking5','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_5.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking.png')";
        sqLiteDatabase.execSQL(insert18);
        String insert19 ="INSERT INTO hairCut VALUES(19,'breaking6','https://github.com/quockhanh09/data-haircut-prank/raw/main/babysneeze/baby_sneeze_6.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/breaking/breaking.png')";
        sqLiteDatabase.execSQL(insert19);
        String insert20 ="INSERT INTO hairCut VALUES(20,'brup','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_1.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert20);
        String insert21 ="INSERT INTO hairCut VALUES(21,'brup2','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_2.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert21);
        String insert22 ="INSERT INTO hairCut VALUES(22,'brup3','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_3.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert22);
        String insert23 ="INSERT INTO hairCut VALUES(23,'brup4','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_4.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert23);
        String insert24 ="INSERT INTO hairCut VALUES(24,'brup5','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_5.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert24);
        String insert25 ="INSERT INTO hairCut VALUES(25,'brup6','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_6.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert25);
        String insert26 ="INSERT INTO hairCut VALUES(26,'brup7','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_7.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert26);
        String insert27 ="INSERT INTO hairCut VALUES(27,'brup8','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_8.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert27);
        String insert28 ="INSERT INTO hairCut VALUES(28,'brup9','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_9.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert28);
        String insert29 ="INSERT INTO hairCut VALUES(29,'brup10','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_10.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert29);
        String insert30 ="INSERT INTO hairCut VALUES(30,'brup11','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_11.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert30);
        String insert31 ="INSERT INTO hairCut VALUES(31,'brup12','https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/funny_burp_12.mp3', 'https://github.com/quockhanh09/data-haircut-prank/raw/main/burp/ic_burp.png')";
        sqLiteDatabase.execSQL(insert31);
        String createTb_favorite = "create table favorite(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name STRING NOT NULL, sound STRING NOT NULL , image STRING NOT NULL)";
        sqLiteDatabase.execSQL(createTb_favorite);
        String createTB_voice ="create table voice(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name STRING NOT NULL , file STRING NOT NULL , image STRING )";
        sqLiteDatabase.execSQL(createTB_voice);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void suLyFile(){
        File  dbFile = context.getDatabasePath(FILE_Name);
        if(!dbFile.exists()){
            copyDataBase();
        }else{
            dbFile.delete();
            copyDataBase();
        }
    }
    public void copyDataBase(){
        try {
            InputStream myInput =context.getAssets().open(FILE_Name);
            String outFileName = DB_PATH;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length= myInput.read(buffer);
            while (length>0){
                outputStream.write(buffer,0, length);
            }
            outputStream.flush();
            outputStream.close();
            myInput.close();
        }catch (Exception e){
            Log.e("Lỗi copy file", "copyDataBase: "+e );
        }

    }
}
