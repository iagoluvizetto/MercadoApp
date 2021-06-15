package com.projeto.mercadoapp.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Carrinho;
import com.projeto.mercadoapp.models.Usuario;
import com.projeto.mercadoapp.models.UsuarioSessao;
import com.projeto.mercadoapp.ui.home.DetalheFragment;
import com.projeto.mercadoapp.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private LoginButton loginButton;
    private FirebaseAuth mFirebaseAuth;
    private CallbackManager mCallbackManager;
    private TextView textViewUsuario;
    private ImageView imagemPerfil;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login, container, false);

        textViewUsuario  = root.findViewById(R.id.nome);
        imagemPerfil = root.findViewById(R.id.perfil);
        loginButton = root.findViewById(R.id.login_button);
        loginButton.setVisibility(View.INVISIBLE);
        mFirebaseAuth = FirebaseAuth.getInstance();


        Button btnSair = root.findViewById(R.id.button_sair);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//
//                HomeFragment fragment = new HomeFragment();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.re(R.id.linearLayoutlogin, fragment);
//                //fragmentTransaction.addToBackStack(DetalheFragment.class.getName());
//                fragmentTransaction.commit();

                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                updateUI(user);

            }
        });


        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user == null) {
            abreLoginActivity();
        } else {
            updateUI(user);
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        updateUI(user);
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            textViewUsuario.setText(user.getDisplayName());
            if(user.getPhotoUrl()!=null){
                String ImagemURL=user.getPhotoUrl().toString();
                ImagemURL = ImagemURL + "?type=large";
                Picasso.get().load(ImagemURL).into(imagemPerfil);
            }else{
                textViewUsuario.setText("Deslogado!");
                imagemPerfil.setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);
            }
        }
    }


    public void abreLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);


    }


}