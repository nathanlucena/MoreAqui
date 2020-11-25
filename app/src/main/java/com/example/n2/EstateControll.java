package com.example.n2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class EstateControll {

    public Estate insertData(@NonNull SQLiteDatabase db, @NonNull String type, @NonNull String size, String status, double latitude, double longitude, @NonNull String phone){
        try {
            db.execSQL("INSERT INTO MoreAqui_tbl("+ EstatesData.TYPE + ", "+ EstatesData.SIZE + ", "+ EstatesData.STATUS + ", "+ EstatesData.LATITUDE + ", "+ EstatesData.LONGITUDE + ", "+ EstatesData.PHONE + ") VALUES(" + type + "," + size + "," + status +  "," + latitude + "," + longitude + "," + phone +");");
        }catch (Exception e){
            Log.e("Erro de inserção na base de dados: ",e.toString());

        }
        return new Estate(type, size, phone, status);
    }

    //(TYPE, SIZE, STATUS, LATITUDE, LONGITUDE, PHONE)
    public Estate updateData(@NonNull SQLiteDatabase db,@NonNull int ID, @NonNull String type, @NonNull String size, String status, double latitude, double longitude, @NonNull String phone){
        try {
            db.execSQL("UPDATE MoreAqui_tbl SET("+ EstatesData.TYPE + " = '" + type + "', "+ EstatesData.SIZE + " = '" + size + "', "+ EstatesData.STATUS + " = '" + status +  "', "+ EstatesData.LATITUDE + " = '" + latitude + "', "+ EstatesData.LONGITUDE + " = '" + longitude + "', "+ EstatesData.PHONE + " = '" + phone +"') WHERE ("+EstatesData._ID+" = "+ Integer.toString(ID)+");");
        }catch (Exception e){
            Log.e("Erro de inserção na base de dados: ",e.toString());
        }
        return new Estate(type, size, phone, status);
    }
    public boolean deletData(@NonNull SQLiteDatabase db, @NonNull int ID){
        try {
            db.execSQL("DELETE FROM MoreAqui_tbl WHERE (_ID = "+ Integer.toString(ID)+");");
            return true;
        }catch (Exception e){
            Log.e("Erro de inserção na base de dados: ",e.toString());
            return false;
        }
    }

    public ArrayList<ObjectData> selectData(@NonNull SQLiteDatabase db){
        ArrayList<ObjectData> list = new ArrayList<>();

        try {

            //Fazendo query select
            Cursor cursor = db.rawQuery("SELECT * FROM MoreAqui_tbl;", null);

            //Movendo o curso para o primeiro da lista
            cursor.moveToFirst();
            while (cursor != null){

                //Criando objeto para dos dados
                ObjectData data = new ObjectData();

                //Armazenando objeto
                data.setID(cursor.getString(cursor.getColumnIndex(EstatesData._ID)));
                data.setTYPE(cursor.getString(cursor.getColumnIndex(EstatesData.TYPE)));
                data.setSIZE(cursor.getString(cursor.getColumnIndex(EstatesData.SIZE)));
                data.setSTATUS(cursor.getString(cursor.getColumnIndex(EstatesData.STATUS)));
                data.setLATITUDE(cursor.getString(cursor.getColumnIndex(EstatesData.LATITUDE)));
                data.setLONGITUDE(cursor.getString(cursor.getColumnIndex(EstatesData.LONGITUDE)));
                data.setPHONE(cursor.getString(cursor.getColumnIndex(EstatesData.PHONE)));

                //Adicionando a lista

                list.add(data);
                //Alterando para próxima linha
                cursor.moveToNext();
            }
        }catch (Exception e){
            Log.e("Erro de inserção na base de dados: ", e.toString());
        }

        return list;
    }
}
