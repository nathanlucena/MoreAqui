package com.example.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ActivityView extends AppCompatActivity {

    private ListView list;
    private List<LocationEstate> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //abrindo a lista do banco de dados
        dataList = ControllDatabase.getListtData(MainActivity.getDB().Open());

        //verificando se a lista a lista não esta vazia
        if(dataList != null){

            //reetornando a lista
            list = (ListView) findViewById(R.id.listview);
            ArrayAdapter adapter = new ArrayAdapter(ActivityView.this, android.R.layout.simple_list_item_1, android.R.id.text1, dataList);
            list.setAdapter(adapter);

            //caso a lista estja vazia ele irá retornar uma mensagem
        }else{
            Toast.makeText(this, "Lista vazia!!", Toast.LENGTH_SHORT).show();
        }

        //fechando o banco de dados
        MainActivity.getDB().close();

    }
}