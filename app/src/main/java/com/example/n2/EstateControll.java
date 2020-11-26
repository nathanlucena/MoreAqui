package com.example.n2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public abstract class EstateControll{

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
            db.execSQL("INSERT INTO MoreAqui_tbl("+ EstatesData.TYPE + ", "+ EstatesData.SIZE + ", "+ EstatesData.STATUS + ", "+ EstatesData.LATITUDE + ", "+ EstatesData.LONGITUDE + ", "+ EstatesData.PHONE + ") VALUES('" + type + "','" + size + "','" + status +  "'," + latitude + "," + longitude + ",'" + phone +"');");
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
            db.execSQL("UPDATE "+ EstatesData.TABLE_NAME+" SET "+ EstatesData.TYPE + " = '" + type + "', "+ EstatesData.SIZE + " = '" + size + "', "+ EstatesData.STATUS + " = '" + status +  "', "+ EstatesData.LATITUDE + " = " + latitude + ", "+ EstatesData.LONGITUDE + " = " + longitude + ", "+ EstatesData.PHONE + " = '" + phone +"' WHERE ( "+EstatesData.TABLE_NAME+"."+EstatesData._ID+"="+ Integer.toString(ID)+" );");
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
            db.execSQL("DELETE FROM "+EstatesData.TABLE_NAME+" WHERE "+EstatesData._ID+" = "+ Integer.toString(ID)+";");
            return true;
        }catch (Exception e){
            Log.e("Erro ao excluir: ",e.toString());
            return false;
        }
    }

    /***
     * Metodo para buscar os itens da tabela MoreAqui_tbl
     * @param (@NonNull SQLiteDatabase db)
     * @return ArrayList<ObjectData>
     ***/
    public static ArrayList<ObjectData> selectData(@NonNull SQLiteDatabase db){
        ArrayList<ObjectData> list = new ArrayList<>();

        try {

            //Fazendo query select
            Cursor cursor = db.rawQuery("SELECT * FROM "+EstatesData.TABLE_NAME+";", null);

            //Movendo o curso para o primeiro da lista
            if(cursor.moveToFirst()){
                do{
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
                }while(cursor.moveToNext());

                return list;

            }else{

                return null;

            }
        }catch (Exception e){
            Log.e("Erro de exibição: ", e.toString());

            return null;

        }
    }
}
