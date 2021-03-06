package com.example.moreaqui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConfDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String TABLE_NAME = "MoreAqui_tbl", _ID = "ID", TYPE = "TYPE", SIZE = "SIZE", STATUS = "STATUS", LATITUDE = "LATITUDE", LONGITUDE = "LONGITUDE", PHONE = "PHONE";
    private static final
    String DATABASE_NAME = "MoreAqui",
           CreateTable_MoreAqui = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + "(" +
                   _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   TYPE +" TEXT NOT NULL, " +
                   SIZE +" TEXT NOT NULL, " +
                   STATUS +" TEXT, " +
                   LATITUDE +" DOUBLE, " +
                   LONGITUDE +" DOUBLE, " +
                   PHONE +" TEXT NOT NULL);";

    public ConfDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Classe responsável por retornar o endereço do banco
     * @return SQLiteDatabase
     */
    public SQLiteDatabase Open(){
        return getWritableDatabase();
    }

    public void Close(){
        close();
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(CreateTable_MoreAqui);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MoreAqui_tbl");
        onCreate(db);
    }
}
