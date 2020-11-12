package com.example.n2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.n2.Estate;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.os.Handler;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.Toast;
import android.view.View;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class CriarActivity extends AppCompatActivity {

    private EditText edt_fone;
    private RadioGroup radio_tipo;
    private RadioGroup radio_tamanho;
    private String tamanhoString;
    private String tipoString;
    private boolean checkStatus;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar1);

        edt_fone = (EditText) findViewById(R.id.edt_fone);
        radio_tipo = findViewById(R.id.radio_tipo);
        radio_tamanho = findViewById(R.id.radio_tamanho);


        SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edt_fone, smf);
        edt_fone.addTextChangedListener((mtw));

        }


        public void Btn_validar(View v) {
            if(edt_fone.length()!=14 || tipoString==null || tamanhoString==null){
                Toast.makeText(this, "Preencha todos os dados corretamente", Toast.LENGTH_SHORT).show();
            }else{
                Estate estate;
                estate = new Estate(tipoString,tamanhoString, edt_fone.getText().toString(),status);
                //Toast.makeText(this, estate.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CriarActivity.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Local criado com sucesso!!", Toast.LENGTH_SHORT).show();
            }
        }

        public void RadioGroupTamanho(View v){

            int itemRadGroupTamanho = radio_tamanho.getCheckedRadioButtonId();

            if(itemRadGroupTamanho != -1){
                RadioButton tamSelecionado = findViewById(itemRadGroupTamanho);

                 tamanhoString = tamSelecionado.getText().toString();

               // Toast.makeText(this, tamanhoString, Toast.LENGTH_SHORT).show();
            }
        }

        public void RadioGroupTipo(View v) {

            int itemRadGroupTipo = radio_tipo.getCheckedRadioButtonId();

            if (itemRadGroupTipo != -1) {
                RadioButton tipoSelecionado = findViewById(itemRadGroupTipo);

                tipoString = tipoSelecionado.getText().toString();

              //  Toast.makeText(this, tipoString, Toast.LENGTH_SHORT).show();
            }
        }

        public void CheckBox(View v) {
            checkStatus = ((CheckBox) v).isChecked();
            if (checkStatus == true) {
                status = "Em construção";
            } else {
                status = "Não está em construção";
            }

        }
}

