package com.example.moreaqui;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static ConfDatabase db;

    //Permissões
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Chama a janela para pedir permissão
        Permissoes.validarPermissoes(permissoes, this, 1);

        //Iniciando/criando banco SQLite com o contexto da view main activity
        db = new ConfDatabase(getBaseContext());

    }

    //abre a activity de criação
    public void eventCriar(View v){
        Intent intent = new Intent(MainActivity.this, CriarActivity.class);
        startActivity(intent);
    }

    //abre a activity de vizualização
    public void eventVizualizar(View v){
        Intent intent = new Intent(MainActivity.this, ActivityView.class);
        startActivity(intent);
    }

    //abre a activity para ver o mapa
    public void eventMapa(View v){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    //guarda os dados no servidor
    public void eventGuardar(View v){
        try {
            List<LocationEstate> estates = ControllDatabase.getListtData(db.Open());

            new RecordData().execute(estates);

            Log.i("Estates: ", estates.toString());

            Toast.makeText(this, "Base de dados submetida com sucesso!!", Toast.LENGTH_SHORT).show();

            db.close();
        }catch(Exception e){
            Toast.makeText(this, "Erro ao submeter os dados!!", Toast.LENGTH_SHORT).show();
            Log.e("Submissão fracassada: ", e.toString());
        }
    }

    public static ConfDatabase getDB(){
        return db;
    }

}