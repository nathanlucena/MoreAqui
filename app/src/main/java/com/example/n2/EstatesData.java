package com.example.n2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EstatesData extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String _ID = "ID", TYPE = "TYPE", SIZE = "SIZE", STATUS = "STATUS", LATITUDE = "LATITUDE", LONGITUDE = "LONGITUDE", PHONE = "PHONE";
    private static final
    String DATABASE_NAME = "MoreAqui",
           CreateTable_MoreAqui = "CREATE TABLE IF NOT EXISTS MoreAqui_tbl(" +
                   _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   TYPE +" TEXT NOT NULL, " +
                   SIZE +" TEXT NOT NULL, " +
                   STATUS +" TEXT, " +
                   LATITUDE +" DOUBLE, " +
                   LONGITUDE +" DOUBLE, " +
                   PHONE +" TEXT NOT NULL);";

    public EstatesData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
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
