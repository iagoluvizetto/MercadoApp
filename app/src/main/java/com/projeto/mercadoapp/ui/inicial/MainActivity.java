package com.projeto.mercadoapp.ui.inicial;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Carrinho;
import com.projeto.mercadoapp.models.CarrinhoItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    public static Context instance;
    private BadgeDrawable badge;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = getApplicationContext();

        setContentView(R.layout.activity_main);
         navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_carrinho, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Carrinho carrinho = Carrinho.getInstancia();
        carrinho.setActivity(this);
    }

    public void updateCartCount(int quantidade){
        if (badge == null){
            badge = navView.getOrCreateBadge(R.id.navigation_carrinho);
        }

        badge.setNumber(quantidade);
    }

    public static Context getInstance() {
        return instance;
    }
}