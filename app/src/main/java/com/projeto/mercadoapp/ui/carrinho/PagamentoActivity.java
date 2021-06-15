package com.projeto.mercadoapp.ui.carrinho;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.ui.home.HomeFragment;
import com.projeto.mercadoapp.ui.inicial.MainActivity;
import com.projeto.mercadoapp.ui.perfil.LoginActivity;
import com.projeto.mercadoapp.models.Carrinho;


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
                } else
                    Toast.makeText(PagamentoActivity.this, "Pedido Finalizado", Toast.LENGTH_SHORT).show();

                zerarCarrinho();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);



//                concluirPagamento.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v){
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
//                   }
//                });
            }
        });
    }

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

    public void zerarCarrinho(){
        Carrinho carrinho = Carrinho.getInstancia();
        carrinho.removerTodos();
    }

}


