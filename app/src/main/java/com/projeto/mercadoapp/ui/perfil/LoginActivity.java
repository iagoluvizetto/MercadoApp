package com.projeto.mercadoapp.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.mercadoapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button cadastrar = (Button) findViewById(R.id.button_cadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                abreCadastroActivity(v);
            }

        });
    }
    public void abreCadastroActivity(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);

    }
}