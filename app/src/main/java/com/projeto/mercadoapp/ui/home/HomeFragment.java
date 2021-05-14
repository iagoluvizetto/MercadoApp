package com.projeto.mercadoapp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.JsonPlaceHolderApi;
import com.projeto.mercadoapp.models.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment
        extends Fragment
        implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private FragmentActivity productFragmentContext;//Conseguir acessar a activity para criar outros fragmentos
    Context thisContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        thisContext = container.getContext();

        final TextView textView = root.findViewById(R.id.text_home);
        final Button hortifrutiButton = root.findViewById(R.id.hortifrutiButton);
        final Button carnesButton = root.findViewById(R.id.carnesButton);


        //Observa click no menu inferior
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        hortifrutiButton.setOnClickListener(this);
        carnesButton.setOnClickListener(this);

        return root;
    }

    @Override//Associar esse fragment ao contexto da activity principal - não mexer (magia negra)
    public void onAttach(Activity activity) {
        productFragmentContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.hortifrutiButton:
                setFragment(new ProdutosFragment(), "hortifruti");
                break;
            case R.id.carnesButton:
                setFragment(new ProdutosFragment(), "carnes");
                break;
            case R.id.buttonOi:
                //Teste
                break;
        }
    }

    private void changeTextTest(View v) {
        TextView tv = v.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText(s);
            }
        });
    }

    private void setFragment(Fragment fragment, String category) {
        Bundle bundle = new Bundle();
        String cat = category;
        bundle.putString("category", cat);
        FragmentManager fragmentManager = productFragmentContext.getSupportFragmentManager();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutProdutos, fragment);
        fragmentTransaction.commit();
    }

}