package com.projeto.mercadoapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.projeto.mercadoapp.databinding.FragmentDetalheBinding;
import com.projeto.mercadoapp.models.Carrinho;
import com.projeto.mercadoapp.models.CarrinhoItem;
import com.projeto.mercadoapp.ui.inicial.MainActivity;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Produto;

import java.net.CacheRequest;

public class DetalheFragment extends Fragment {

    private Produto produto;

    public DetalheFragment() {
    }


    public static DetalheFragment newInstance(Produto produto) {
        DetalheFragment fragment = new DetalheFragment();
        Bundle args = new Bundle();
        args.putParcelable("produto", produto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Produto p  = getArguments().getParcelable("produto");
            this.produto = p;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       FragmentDetalheBinding binding = FragmentDetalheBinding.inflate(inflater, container, false);

        binding.setProduto(produto);

        Glide.with(binding.imgProduto)
                .load("https://" + produto.getImg())
                .into(binding.imgProduto);
        binding.imgProduto.setAdjustViewBounds(true);

        binding.btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int qtd = Integer.valueOf(binding.editQtd.getText().toString());
                CarrinhoItem carrinhoItem = new CarrinhoItem(produto, qtd);
                Carrinho carrinho = Carrinho.getInstancia();
                carrinho.adicionar(carrinhoItem);

            }
        });

        binding.btnMais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               int valor = Integer.parseInt(binding.editQtd.getText().toString());
               valor = valor + 1;
                binding.editQtd.setText("" + valor);
            }
        });

        binding.btnMenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int valor = Integer.parseInt(binding.editQtd.getText().toString());
                if(valor > 1) {
                    valor = valor - 1;
                    binding.editQtd.setText("" + valor);
                }
            }
        });
        return binding.getRoot();

    }
}