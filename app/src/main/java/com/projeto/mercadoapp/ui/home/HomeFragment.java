package com.projeto.mercadoapp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

import com.projeto.mercadoapp.R;

public class HomeFragment
        extends Fragment
        implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private FragmentActivity productFragmentContext;//Conseguir acessar a activity para criar outros fragmentos
    Context thisContext;
    private Button hortifrutiButton;
    private Button merceariaButton;
    private Button fiambreriaButton;
    private Button carnesButton;
    private Button bebidasButton;
    private Button higieneButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        thisContext = container.getContext();

        //final TextView textView = root.findViewById(R.id.text_home);

        hortifrutiButton = root.findViewById(R.id.hortifrutiButton);
        merceariaButton = root.findViewById(R.id.merceariaButton);
        fiambreriaButton = root.findViewById(R.id.fiambreriaButton);
        carnesButton = root.findViewById(R.id.carnesButton);
        bebidasButton = root.findViewById(R.id.bebidasButton);
        higieneButton = root.findViewById(R.id.higieneButton);

        hortifrutiButton.setOnClickListener(this);
        merceariaButton.setOnClickListener(this);
        fiambreriaButton.setOnClickListener(this);
        carnesButton.setOnClickListener(this);
        bebidasButton.setOnClickListener(this);
        higieneButton.setOnClickListener(this);

        hortifrutiButton.callOnClick();

        //Observa click no menu inferior
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });



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
                v.setSelected(true);
                handleButtonSelection(v.getId());
                setFragment(new ProdutosFragment(), "hortifruti");
                break;
            case R.id.merceariaButton:
                v.setSelected(true);
                handleButtonSelection(v.getId());
                setFragment(new ProdutosFragment(), "mercearia");
                break;
            case R.id.fiambreriaButton:
                v.setSelected(true);
                handleButtonSelection(v.getId());
                setFragment(new ProdutosFragment(), "fiambreria");
                break;
            case R.id.carnesButton:
                v.setSelected(true);
                handleButtonSelection(v.getId());
                setFragment(new ProdutosFragment(), "carnes");
                break;
            case R.id.bebidasButton:
                v.setSelected(true);
                handleButtonSelection(v.getId());
                setFragment(new ProdutosFragment(), "bebidas");
                break;
            case R.id.higieneButton:
                v.setSelected(true);
                handleButtonSelection(v.getId());
                setFragment(new ProdutosFragment(), "higiene");
                break;
        }
    }

    public void handleButtonSelection(int i) {
        if(hortifrutiButton.getId() != i){
            hortifrutiButton.setSelected(false);
        }
        if(merceariaButton.getId() != i){
            merceariaButton.setSelected(false);
        }
        if(fiambreriaButton.getId() != i){
            fiambreriaButton.setSelected(false);
        }
        if(carnesButton.getId() != i){
            carnesButton.setSelected(false);
        }
        if(bebidasButton.getId() != i){
            bebidasButton.setSelected(false);
        }
        if(higieneButton.getId() != i){
            higieneButton.setSelected(false);
        }
    }

    private void changeTextTest(View v) {
        //TextView tv = v.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //tv.setText(s);
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