package com.projeto.mercadoapp.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("produtos")
    Call<List<Produto>> getProdutos();

    @GET("produtos")
    Call<List<Produto>> getProdutosByCategory(@Query("categoria") String category);

    @GET("produtos/{id}")
    Call<Produto> getProduto(@Path("id") int id);
}
