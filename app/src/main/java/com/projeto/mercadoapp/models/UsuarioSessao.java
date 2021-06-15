package com.projeto.mercadoapp.models;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsuarioSessao {

    private Usuario usuario;

    private static UsuarioSessao instancia;


    public static Usuario loadUsuario(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("sessao", activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("usuario", null);
        Type type = new TypeToken<Usuario>() {}.getType();
        Usuario usuario = gson.fromJson(json, type);
        return usuario;
    }


    public static void saveUsuario(Activity activity, Usuario usuario) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("sessao", activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        editor.putString("usuario", json);
        editor.apply();

    }

}
