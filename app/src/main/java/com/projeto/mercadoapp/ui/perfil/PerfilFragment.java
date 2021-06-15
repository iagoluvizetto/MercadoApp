package com.projeto.mercadoapp.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Usuario;
import com.projeto.mercadoapp.models.UsuarioSessao;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login, container, false);
        Usuario usuario = UsuarioSessao.loadUsuario(getActivity());

        return root;
    }


    public void abreLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);


    }

    public void exibePerfil(View root, Usuario usuario) {
        TextView email = root.findViewById(R.id.tv_usuario_email);
        email.setText(usuario.getEmail());

    }
}