package com.projeto.mercadoapp.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Produto {

    private int id;
    private String nome;
    private String img;
    private String categoria;
    private double preco;

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

    public String getPrecoStr() {
        Locale localeBR = new Locale( "pt", "BR" );
        NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
        return dinheiroBR.format(preco);
    }

    public String getCategoria() {
        return categoria;
    }
}
