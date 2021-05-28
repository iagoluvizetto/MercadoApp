package com.projeto.mercadoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.models.Carrinho;
import com.projeto.mercadoapp.models.CarrinhoItem;
import com.projeto.mercadoapp.models.Produto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    Carrinho carrinho;
    List<CarrinhoItem> lista;


    public CarrinhoAdapter() {
        carrinho = Carrinho.getInstancia();
        lista = carrinho.getListaitens();
    }

    @NonNull
    @NotNull
    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho2, parent, false);
        return new CarrinhoAdapter.CarrinhoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CarrinhoViewHolder holder, int position) {
        CarrinhoItem ci  = lista.get(position);
        Produto p = ci.getProduto();

        holder.nome.setText(p.getNome());
        holder.preco.setText(p.getPrecoStr());
        holder.quantidade.setText("" + ci.getQuantidade());

        Glide.with(holder.itemView)
                .load("https://" + p.getImg())
                .into(holder.imagem);
        holder.imagem.setAdjustViewBounds(true);


            }

    @Override
    public int getItemCount() {
        return lista.size();

    }

    public class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        public TextView quantidade;
        public TextView nome;
        public TextView preco;
        public ImageView imagem;


        public CarrinhoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

        quantidade = itemView.findViewById(R.id.tv_cr_qtdade2);
        nome = itemView.findViewById(R.id.tv_cr_nome2);
        preco = itemView.findViewById(R.id.tv_cr_preco2);
        imagem = itemView.findViewById(R.id.image_cr2);
        }


    }
}