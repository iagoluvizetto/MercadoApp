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

       View view = inflater.inflate(R.layout.fragment_detalhe, container, false);

        TextView tvNome = view.findViewById(R.id.txt_nome);
        TextView tvPreco = view.findViewById(R.id.txt_preço);
        ImageView imgProduto = view.findViewById(R.id.img_produto);


        tvPreco.setText(produto.getPrecoStr());
        tvNome.setText(produto.getNome()+"");

        Glide.with(imgProduto)
                .load("https://" + produto.getImg())
                //.error(R.drawable.ic_loading)
                //.placeholder(R.drawable.ic_loading)
                .into(imgProduto);
        imgProduto.setAdjustViewBounds(true);

       Button btMais = view.findViewById(R.id.btn_mais);
       Button btMenos = view.findViewById(R.id.btn_menos);
       EditText editeQtd = view.findViewById(R.id.edite_qtd);

        Button btAdd = view.findViewById(R.id.btn_adicionar);

        btAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int qtd = Integer.valueOf(editeQtd.getText().toString());

                CarrinhoItem carrinhoItem = new CarrinhoItem(produto, qtd);

                Carrinho carrinho = Carrinho.getInstancia();
                carrinho.adicionar(carrinhoItem);






            }
        });

        btMais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               int valor = Integer.parseInt(editeQtd.getText().toString());
               valor = valor + 1;
                editeQtd.setText("" + valor);
            }
        });

        btMenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int valor = Integer.parseInt(editeQtd.getText().toString());
                if(valor > 1) {
                    valor = valor - 1;
                    editeQtd.setText("" + valor);
                }
            }
        });
        return view;

    }
}