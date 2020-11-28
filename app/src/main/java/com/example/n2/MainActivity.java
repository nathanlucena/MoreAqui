package com.example.n2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    //Conexão com a base de dados
    private static ConfDatabase db;

    Button btn_criar;
    Button btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //Obtendo objeto para base
//        db = new ConfDatabase(getBaseContext());
//        SQLiteDatabase temp = db.Open();
//        ArrayList<ObjectDatabase> list = ControllDatabase.selectData(temp);
//        if(list != null)
//            for(int i = 0; i < list.size(); i++){
//                Log.e("Objeto: ", list.get(i).toString());
//            }
//        else
//            Log.e("Exibição: ","Veio null");


        btn_criar = (Button) findViewById(R.id.btn_criar);
        btn_view =  (Button) findViewById(R.id.btn_view);

        //BOTAO QUE VAI  ATE CriarActivity
         btn_criar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CriarActivity.class);
                startActivity(intent);
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityView.class);
                startActivity(intent);
            }
        });
    }

    public void sendServer(View v){


    }

    public static ConfDatabase getDataBase(){
        return db;
    }
}