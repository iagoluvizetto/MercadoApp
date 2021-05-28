package com.projeto.mercadoapp.models;

import android.content.Context;

import com.projeto.mercadoapp.ui.inicial.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<CarrinhoItem> listaitens;
    private double valorTotal;
    private int quantidade;

    private static Carrinho instancia;

    public static Carrinho getInstancia() {
        if (instancia == null) instancia = new Carrinho();
        return instancia;
    }

    private Carrinho() {
        listaitens = new ArrayList<>();
    }

    public List<CarrinhoItem> getListaitens() {
        return listaitens;
    }

    public void setListaitens(List<CarrinhoItem> listaitens) {
        this.listaitens = listaitens;
    }

    public void adicionar(CarrinhoItem c){
        listaitens.add(c);

    }


    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
