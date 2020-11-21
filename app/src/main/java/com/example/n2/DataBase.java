package com.example.n2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final int VERSION = 1; //versao do bd
    private static final String DATABASE = "moreaqui"; //nome do bd

    private static final String TABLE_NAME = "tabelamoreaqui"; // nome da tabela
    private static final String _ID = "id"; //coluna id
    private static final String COLUNA_TELEFONE = "telefone";  // coluna telefone
    private static final String COLUNA_TIPOIMOVEL = "tipo"; //coluna tipo se eh casa,apartamento,etc.
    private static final String COLUNA_TAMANHO = "tamanho"; //coluna tamando da casa
    private static final String COLUNA_TIPOCONST = "status"; // coluna com o status da construcao, se esta pronto/ em construcao

    /**
     *  Construtor que recebe como parametro o context
     * @param context contexto
     */

    public DataBase(Context context){
        super(context, DATABASE, null, VERSION);
    }

    /**
     * Cria o banco de dados caso  mesmo nao exista
     * @param db banco de dados do tipo SQL database
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria o banco de dados executando uma instrucao em SQL
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_TELEFONE + "TEXT, " + COLUNA_TIPOIMOVEL + "TEXT, "
                + COLUNA_TAMANHO + "TEXT, " + COLUNA_TIPOCONST + "TEXT);");

        //instancia o objeto para inserir no banco de dados
        ContentValues cv=new ContentValues();
        cv.put(COLUNA_TELEFONE, "Telefone");
        cv.put(COLUNA_TIPOIMOVEL, "Tipo");
        cv.put(COLUNA_TAMANHO, "Tamanho");
        cv.put(COLUNA_TIPOCONST, "Construcao");
        // insire o cv dentro do banco de dados
        db.insert(TABLE_NAME, null, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null  );
        return data;
    }
}
