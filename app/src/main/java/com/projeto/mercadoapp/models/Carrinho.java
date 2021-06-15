package com.projeto.mercadoapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projeto.mercadoapp.ui.inicial.MainActivity;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Carrinho {

    private List<CarrinhoItem> listaitens;
    private double valorTotal;
    private int quantidade;
    private FragmentActivity activity;
    private TextView textTotal;
    private Produto produtoDetalhe;

    private static Carrinho instancia;

    public static Carrinho getInstancia() {
        if (instancia == null) instancia = new Carrinho();
        return instancia;
    }

    private Carrinho() {

    }

    public List<CarrinhoItem> getListaitens() {
        return listaitens;
    }

    public void setListaitens(List<CarrinhoItem> listaitens) {
        this.listaitens = listaitens;
    }

    public void adicionar(CarrinhoItem c){
        for (CarrinhoItem item : listaitens) {
            if(item.getProduto().getId() == c.getProduto().getId()){
                item.setQuantidade(item.getQuantidade() + c.getQuantidade());
                atualizarItens();
                return;

            }
        }
                listaitens.add(c);
                atualizarItens();
    }

    public void atualizarItens(){
        quantidade = 0;
        valorTotal = 0;
        for (CarrinhoItem item : listaitens) {
            valorTotal = valorTotal + item.getQuantidade() * item.getProduto().getPreco();
            quantidade = quantidade + item.getQuantidade();
        }
        ((MainActivity)activity).updateCartCount(quantidade);
        if(textTotal != null){
            textTotal.setText(getValorTotalStr());
        }
        saveData();
    }

    public void remover(CarrinhoItem c){
//        for (CarrinhoItem item : listaitens) {
//           if(item.getProduto().getId() == c.getProduto().getId()){
//               listaitens.remove(item);
//           }
//        }
        for (Iterator<CarrinhoItem> it = listaitens.iterator(); it.hasNext(); ) {
            CarrinhoItem item = it.next();
            if (item.getProduto().getId() == c.getProduto().getId()) {
                it.remove();
            }
        }
        atualizarItens();
    }

    public String getValorTotalStr() {
        Locale localeBR = new Locale( "pt", "BR" );
        NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
        return dinheiroBR.format(valorTotal);
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

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
        loadData();
    }

    public void setTextTotal(TextView textTotal) {
        this.textTotal = textTotal;
        atualizarItens();
    }
    private void loadData() {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("carrinho", activity.MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString("produtos", null);

        Type type = new TypeToken<ArrayList<CarrinhoItem>>() {}.getType();

        listaitens = gson.fromJson(json, type);

        if (listaitens == null) {

            listaitens = new ArrayList<>();
            return;
        }
        atualizarItens();
    }

    public Produto getProdutoDetalhe() {
        return produtoDetalhe;
    }

    public void setProdutoDetalhe(Produto produtoDetalhe) {
        this.produtoDetalhe = produtoDetalhe;
    }

    private void saveData() {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("carrinho", activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(listaitens);

        editor.putString("produtos", json);

        editor.apply();

    }

    public void removerTodos(){
        listaitens.clear();
        atualizarItens();
    }
}

