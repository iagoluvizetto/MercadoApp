package com.projeto.mercadoapp.ui.perfil;
//Android import
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//facebook import
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.CallbackManager;
//Tasks import
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//Firebase import
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.squareup.picasso.Picasso;

import com.projeto.mercadoapp.R;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    private TextView textViewUsuario;
    private LoginButton loginButton;
    private String TAG = "FacebookAuthenticon";
    private ImageView imagemPerfil;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        Button btnSair = findViewById(R.id.button_sair);
        btnSair.setVisibility(View.INVISIBLE);

        Log.d(TAG,"Iniciou! ");
        textViewUsuario  =findViewById(R.id.nome);
        imagemPerfil = findViewById(R.id.perfil);
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"login realizado: " +
                        "" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                Log.d(TAG,"Login Cancelado! ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"Deu Erro!" +
                        "" + error);
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    updateUI(user);
                }else{
                    updateUI(null);
                }
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null){
                    mFirebaseAuth.signOut();
                }
            }
        };
    }

    private void handleFacebookToken(AccessToken accessToken) {
        Log.d(TAG,"handlefacebook: " + accessToken);
        AuthCredential credential = FacebookAuthProvider.getCredential(
                accessToken.getToken());

        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"Login com Facebook: Sucesso!");
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Log.d(TAG,"Login com Facebook: Falhou!,", task.getException());
                    Toast.makeText(LoginActivity.this, "Autenticação Falhou", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            finish();
//            textViewUsuario.setText(user.getDisplayName());
//            if(user.getPhotoUrl()!=null){
//                String ImagemURL=user.getPhotoUrl().toString();
//                ImagemURL = ImagemURL + "?type=large";
//                Picasso.get().load(ImagemURL).into(imagemPerfil);
//            }else{
//                textViewUsuario.setText("Deslogado!");
//                imagemPerfil.setImageResource(R.drawable.com_facebook_profile_picture_blank_portrait);
//            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onDestroy() {
        accessTokenTracker.stopTracking();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            mFirebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
