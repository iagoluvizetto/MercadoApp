package com.projeto.mercadoapp.ui.inicial;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projeto.mercadoapp.BuildConfig;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Carrinho;
import com.projeto.mercadoapp.models.Produto;

import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    public static Context getInstance() {
        return instance;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_share:
                share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share(){

        Produto produto = Carrinho.getInstancia().getProdutoDetalhe();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
//        shareIntent.setPackage("com.whatsapp");
        shareIntent.setPackage("org.telegram.messenger");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MercadoApp");
        String shareMessage= "\nVeja esta oferta no MercadoApp: \n" + produto.getNome() + "  " + "Preço: " + produto.getPrecoStr()+ "\n";
        shareMessage = shareMessage + "http://mercado-api-mobile.herokuapp.com/produtos/" + produto.getId();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        try {
            startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {

        }


    }

}