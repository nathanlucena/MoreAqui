package com.example.moreaqui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.Toast;
import android.util.Log;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class CriarActivity extends AppCompatActivity {

    private EditText edt_fone;
    private RadioGroup radio_tipo;
    private RadioGroup radio_tamanho;
    private String tamanhoString;
    private String tipoString;
    private boolean checkStatus = false;
    private String status = "Não está em construção";
    private double latitude;
    private double longitude;

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar1);

        edt_fone = (EditText) findViewById(R.id.edt_fone);
        radio_tipo = findViewById(R.id.radio_tipo);
        radio_tamanho = findViewById(R.id.radio_tamanho);

        client = LocationServices.getFusedLocationProviderClient(this);

        //MASCARA PARA O NUMERO DE TELEFONE
        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edt_fone, smf);
        edt_fone.addTextChangedListener((mtw));

    }

    @Override
    protected void onResume(){
        super.onResume();


        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.i("Teste", location.getLatitude() + " " + location.getLongitude());
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            Log.e("teste", latitude +""+ longitude);
                        }else{
                            Log.e("teste", "Nada");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    //FUNÇÃO QUE CHAMA O BOTÃO PARA  VALIDAR E VOLTAR PARA MainActivity
    public void Btn_validar(View v) {
        //VERIFICAÇÃO SE OS DADOS ESTÃO PREENCHIDOS CORRETAMENTES
        if (edt_fone.length() != 14 || tipoString == null || tamanhoString == null) {
            Toast.makeText(this, "Preencha todos os dados corretamente", Toast.LENGTH_SHORT).show();
        } else {
                //PEGA TODOS OS DADOS  PREENCHIDOS PELO  USUARIO
                Estate estate;
                estate = new Estate(tipoString, tamanhoString, edt_fone.getText().toString(), status);
                //Toast.makeText(this, estate.toString(), Toast.LENGTH_SHORT).show()

                setLocation();;
                //Sempre que utilizar o banco usar o endereço de MainActivity.getDB()
                ControllDatabase.insertData(MainActivity.getDB().Open(), tipoString, tamanhoString, status, latitude, longitude, edt_fone.getText().toString());
                //Fechando a escrita do banco
                MainActivity.getDB().Close();


            //VOLTA PARA A MainActivity
                Intent intent = new Intent(CriarActivity.this, MainActivity.class);
                startActivity(intent);
                //PASSA A MENSAGEM QUE CONFIRMA QUE A CLASS Estate RECEBEU OS DADOS
                Toast.makeText(this, "Local criado com sucesso!!", Toast.LENGTH_SHORT).show();
            }
        }

        //FUNCAO QUE VERIFICA QUAL O TAMANHO ESCOLHIDO
        public void RadioGroupTamanho(View v){

            int itemRadGroupTamanho = radio_tamanho.getCheckedRadioButtonId();

            if(itemRadGroupTamanho != -1){
                RadioButton tamSelecionado = findViewById(itemRadGroupTamanho);

                 tamanhoString = tamSelecionado.getText().toString();

               // Toast.makeText(this, tamanhoString, Toast.LENGTH_SHORT).show();
            }
        }

    //FUNCAO QUE VERIFICA QUAL O TIPO ESCOLHIDO
        public void RadioGroupTipo(View v) {

            int itemRadGroupTipo = radio_tipo.getCheckedRadioButtonId();

            if (itemRadGroupTipo != -1) {
                RadioButton tipoSelecionado = findViewById(itemRadGroupTipo);

                tipoString = tipoSelecionado.getText().toString();

              //  Toast.makeText(this, tipoString, Toast.LENGTH_SHORT).show();
            }
        }

        //FUNCAO QUE VERIFICA SE O IMOVEL ESTÁ EM CONSTRUÇÃO
        public void CheckBox(View v) {
            checkStatus = ((CheckBox) v).isChecked();
            if (checkStatus) {
                status = "Não está em construção";
            } else {
                status = "Em construção";
            }
        }

    public void setLocation(){
        Log.e("teste", "Ferificando permissão");
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }
}

