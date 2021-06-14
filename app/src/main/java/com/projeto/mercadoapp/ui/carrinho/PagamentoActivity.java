package com.projeto.mercadoapp.ui.carrinho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.mercadoapp.R;


public class PagamentoActivity extends AppCompatActivity {


    EditText campoCep, campoEndereco, campoEnderecoNumero, campoEnderecoComplemento, campoBairro, campoCidade, campoValor;
    RadioButton radioButton, dinheiro;
    RadioGroup radioGroup;
    Button concluirPagamento;
    private String tipoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        inicializarComponentes();

        Bundle bundle = getIntent().getExtras();

        //concluirPagamento.setOnClickListener(new View.OnClickListener(){
        //    public void onClick(View v){
        //        int radioId = radioGroup.getCheckedRadioButtonId();
        //       radioButton = findViewById(radioId);
        //    }
        //});
        //Alana

        concluirPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String cep = campoCep.getText().toString();
                String endereco = campoEndereco.getText().toString();
                String enderecoNumero = campoEnderecoNumero.getText().toString();
                String bairro = campoBairro.getText().toString();
                String cidade = campoCidade.getText().toString();
                String valor = campoValor.getText().toString();

                if (cep.isEmpty() || endereco.isEmpty() || enderecoNumero.isEmpty() || bairro.isEmpty() || cidade.isEmpty()){
                    Toast.makeText(PagamentoActivity.this, "Preencha os campos acima", Toast.LENGTH_SHORT).show();
                } else if (dinheiro.isChecked() && valor.isEmpty()) {
                    Toast.makeText(PagamentoActivity.this, "Preencha o valor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //public void onClick(View v){
    //    int radioId = radioGroup.getCheckedRadioButtonId();
    //    radioButton = findViewById(radioId);

    //    if (!radioButton.isChecked()){
    //        Toast.makeText(PagamentoActivity.this, "Selecione um método de pagamento", Toast.LENGTH_SHORT).show();
    //    }
    //}

    private void inicializarComponentes(){
        campoCep = findViewById(R.id.edtCep);
        campoEndereco = findViewById(R.id.edtEndereco);
        campoEnderecoNumero = findViewById(R.id.edtEnderecoNumero);
        campoEnderecoComplemento = findViewById(R.id.edtEnderecoComlemento);
        campoBairro = findViewById(R.id.edtBairro);
        campoCidade = findViewById(R.id.edtCidade);
        concluirPagamento = findViewById(R.id.bt_concluir);
        radioGroup = findViewById(R.id.radioGroup);
        dinheiro = findViewById(R.id.radioButtonDinheiro);
        campoValor = findViewById(R.id.edtValor);
    }
}


