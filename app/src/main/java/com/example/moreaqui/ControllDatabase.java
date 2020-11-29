package com.example.moreaqui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ControllDatabase {

    /***
     * Metodo de inserção de dados na base de dados
     * @param  db @NonNull SQLiteDatabase
     * @param  type @NonNull String
     * @param  size @NonNull String
     * @param status String
     * @param latitude double
     * @param longitude double
     * @param phone @NonNull String
     * @return Estate
     ***/
    public static Estate insertData(@NonNull SQLiteDatabase db, @NonNull String type, @NonNull String size, String status, double latitude, double longitude, @NonNull String phone){
        try {
            db.execSQL("INSERT INTO MoreAqui_tbl("+ ConfDatabase.TYPE + ", "+ ConfDatabase.SIZE + ", "+ ConfDatabase.STATUS + ", "+ ConfDatabase.LATITUDE + ", "+ ConfDatabase.LONGITUDE + ", "+ ConfDatabase.PHONE + ") VALUES('" + type + "','" + size + "','" + status +  "'," + latitude + "," + longitude + ",'" + phone +"');");
        }catch (Exception e){
            Log.e("Erro de inserção na base de dados: ",e.toString());

        }
        return new Estate(type, size, phone, status);
    }

    /***
     * Metodo para atualizar informações na base de dados
     * @param ID @NonNull int
     * @param  db @NonNull SQLiteDatabase
     * @param  type @NonNull String
     * @param  size @NonNull String
     * @param status String
     * @param latitude double
     * @param longitude double
     * @param phone @NonNull String
     * @return Estate
     ***/
    //(TYPE, SIZE, STATUS, LATITUDE, LONGITUDE, PHONE)
    public static Estate updateData(@NonNull SQLiteDatabase db,@NonNull int ID, @NonNull String type, @NonNull String size, String status, double latitude, double longitude, @NonNull String phone){
        try {
            db.execSQL("UPDATE "+ ConfDatabase.TABLE_NAME+" SET "+ ConfDatabase.TYPE + " = '" + type + "', "+ ConfDatabase.SIZE + " = '" + size + "', "+ ConfDatabase.STATUS + " = '" + status +  "', "+ ConfDatabase.LATITUDE + " = " + latitude + ", "+ ConfDatabase.LONGITUDE + " = " + longitude + ", "+ ConfDatabase.PHONE + " = '" + phone +"' WHERE ( "+ ConfDatabase.TABLE_NAME+"."+ ConfDatabase._ID+"="+ Integer.toString(ID)+" );");
        }catch (Exception e){
            Log.e("Erro de inserção na base de dados: ",e.toString());
        }
        return new Estate(type, size, phone, status);
    }
    /***
     * Metodo para apagar informação na base. Necessário o id do item da tabela
     * @param (@NonNull SQLiteDatabase db, @NonNull int ID)
     * @return boolean
     ***/
    public static boolean deleteData(@NonNull SQLiteDatabase db, @NonNull int ID){
        try {
            db.execSQL("DELETE FROM "+ ConfDatabase.TABLE_NAME+" WHERE "+ ConfDatabase._ID+" = "+ Integer.toString(ID)+";");
            return true;
        }catch (Exception e){
            Log.e("Erro ao excluir: ",e.toString());
            return false;
        }
    }

    /***
     * Metodo para buscar os itens da tabela MoreAqui_tbl
     * @param (@NonNull SQLiteDatabase db)
     * @return ArrayList<ObjectDatabase>
     ***/
    public static List<LocationEstate> getListtData(@NonNull SQLiteDatabase db){
        List<LocationEstate> list = new ArrayList<LocationEstate>();

        try {
            //Fazendo query select
            Cursor cursor = db.rawQuery("SELECT * FROM "+ ConfDatabase.TABLE_NAME+";", null);
            //Movendo o curso para o primeiro da lista
            if(cursor.moveToFirst()){
                do{
                    //Criando objeto para por os dados
                    LocationEstate data = new LocationEstate(
                                    cursor.getString(cursor.getColumnIndex(ConfDatabase.TYPE)),
                                    cursor.getString(cursor.getColumnIndex(ConfDatabase.SIZE)),
                                    cursor.getString(cursor.getColumnIndex(ConfDatabase.PHONE)),
                                    cursor.getString(cursor.getColumnIndex(ConfDatabase.STATUS)),
                                    cursor.getDouble(cursor.getColumnIndex(ConfDatabase.LATITUDE)),
                                    cursor.getDouble(cursor.getColumnIndex(ConfDatabase.LONGITUDE)));
                    //Adicionando a lista
                    list.add(data);
                    //Alterando para próxima linha
                }while(cursor.moveToNext());

                return list;

            }else{

                return null;

            }
        }catch (Exception e){
            Log.e("Erro ao obter lista: ", e.toString());

            return null;

        }
    }
}
