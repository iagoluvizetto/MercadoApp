package com.projeto.mercadoapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Produto implements Parcelable {

    private int id;
    private String nome;
    private String img;
    private String categoria;
    private double preco;

    public Produto(int id) {
        this.id = id;
        //this.nome = "produto1";
    }

    public Produto(int id, String nome, String img, String categoria, double preco) {
        this.id = id;
        this.nome = nome;
        this.img = img;
        this.categoria = categoria;
        this.preco = preco;
    }

    private Produto(Parcel p) {
        this.id = p.readInt();
        this.nome = p.readString();
        this.img = p.readString();
        this.categoria = p.readString();
        this.preco = p.readDouble();
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

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nome);
        parcel.writeInt(this.id);

    }

    public double getPreco() {
        return preco;
    }
}
