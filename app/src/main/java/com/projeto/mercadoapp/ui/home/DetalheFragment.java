package com.projeto.mercadoapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Produto;

public class DetalheFragment extends Fragment {

    private Produto produto;


    public DetalheFragment() {

    }


    // TODO: Rename and change types and number of parameters
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

       View view = inflater.inflate(R.layout.fragment_detalhe, container, false);

        TextView tvNome = view.findViewById(R.id.txt_nome);
        TextView tvPreco = view.findViewById(R.id.txt_preço);
        ImageView imgProduto = view.findViewById(R.id.img_produto);

        tvPreco.setText(produto.getPrecoStr());
        tvNome.setText(produto.getNome()+"");

        Glide.with(imgProduto)
                .load("https://" + produto.getImg())
                .error(R.drawable.ic_loading)
                .placeholder(R.drawable.ic_loading)
                .into(imgProduto);
        imgProduto.setAdjustViewBounds(true);


        return view;

    }
}