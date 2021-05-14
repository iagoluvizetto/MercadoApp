package com.projeto.mercadoapp.models;

public class Produto {

    private int id;
    private String nome;
    private String img;

    public Produto(int id) {
        this.id = id;
        //this.nome = "produto1";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImg() { return img; }
}
