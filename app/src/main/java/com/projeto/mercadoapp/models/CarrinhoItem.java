package com.projeto.mercadoapp.models;

public class CarrinhoItem {
    private int quantidade;
    private Produto produto;

    public CarrinhoItem(Produto p, int quantidade){
        this.produto = p;
        this.quantidade = quantidade;

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
