package com.example.mercadoapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mercadoapp.R;
import com.example.mercadoapp.helper.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button botaoLogar;
    private EditText campoEmail, campoSenha;
    private Switch tipoAcesso;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        InicializarComponentes();

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        tipoAcesso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(tipoAcesso.isChecked()){
                    botaoLogar.setText("CADASTRAR...");
                }
                else{
                    botaoLogar.setText("LOGAR...");
                }
            }
        });

        botaoLogar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if(!email.isEmpty() && !senha.isEmpty()){
                    if (tipoAcesso.isChecked()){ //Checkado == Cadastrar
                        autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Cadastro Realizado com sucesso! Efetuando login...", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                }else{

                                }
                            }
                        });
                   }else{ //Não checkado == Login
                        autenticacao.signInWithEmailAndPassword(
                                email, senha
                        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                }else{
                                    Toast.makeText(LoginActivity.this, "Erro! Verifique o usuário/senha e tente novamente! ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Preencha E-mail e Senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void InicializarComponentes(){
        campoEmail = findViewById(R.id.txtEmail);
        campoSenha = findViewById(R.id.txtSenha);
        botaoLogar = findViewById(R.id.buttonLogin);
        tipoAcesso = findViewById(R.id.SwitchLogin);
    }
}