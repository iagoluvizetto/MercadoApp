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
import com.projeto.mercadoapp.models.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<Produto> produtoList;

    public class ProdutoViewHolder extends RecyclerView.ViewHolder {

        public View viewProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewProduto = itemView;
        }
    }

    public ProdutoAdapter(List<Produto> produtos) { this.produtoList = produtos; }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {

        Produto produto = this.produtoList.get(position);

        TextView textView = holder.viewProduto.findViewById(R.id.produtoNome);
        textView.setText(produto.getNome()+"");
        ImageView imageView = holder.viewProduto.findViewById(R.id.produtoImage);
        Glide.with(holder.itemView)
                .load("https://" + produto.getImg())
                //.load("https://homepages.cae.wisc.edu/~ece533/images/zelda.png")
                .error(R.drawable.ic_loading)
                .placeholder(R.drawable.ic_loading)
                .into(imageView);
        imageView.setAdjustViewBounds(true);
//        imageView.setMaxHeight(200);
//        imageView.setMaxWidth(200);

    }

    @Override
    public int getItemCount() { return this.produtoList.size(); }


}
