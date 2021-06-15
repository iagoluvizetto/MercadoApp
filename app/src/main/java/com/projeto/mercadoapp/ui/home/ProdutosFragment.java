package com.projeto.mercadoapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.adapter.ProdutoAdapter;
import com.projeto.mercadoapp.models.Carrinho;
import com.projeto.mercadoapp.models.JsonPlaceHolderApi;
import com.projeto.mercadoapp.models.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProdutosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private Context thisContext;
    private FragmentActivity activity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProdutosFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProdutosFragment newInstance(String param1, String param2) {
        ProdutosFragment fragment = new ProdutosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        String category = bundle.getString("category");
        int idProduto = 0;
        idProduto = bundle.getInt("idProduto");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_produtos, container, false);
        thisContext = container.getContext();

        //TextView textView = view.findViewById(R.id.produtosFragmentTitle);

        //textView.setText(msg);

        callJson(category, container);

        if(idProduto != 0){
            callJsonProduto(idProduto, container);
        }



        return view;

    }

    public void callJson(String category, ViewGroup viewGroup) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mercado-api-mobile.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Produto>> call = jsonPlaceHolderApi.getProdutosByCategory(category);
        call.enqueue(new Callback<List<Produto>>() {

            @Override
            public void onResponse(Call<List<Produto>> call, retrofit2.Response<List<Produto>> response) {
                if(!response.isSuccessful()) {
                    Log.e("deuruim", response.message());
                    return;
                }
                Log.i("deuboa", "ok");

                List<Produto> produtos = response.body();

                GridLayoutManager glm = new GridLayoutManager(thisContext, 2);
                RecyclerView rvProdutos = viewGroup.findViewById(R.id.rvProdutos);
                rvProdutos.setLayoutManager(glm);
                ProdutoAdapter produtoAdapter = new ProdutoAdapter(produtos, getActivity());
                rvProdutos.setAdapter(produtoAdapter);
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Log.e("deuruim", t.getMessage());
            }
        });
    }

    public void callJsonProduto(int idProduto, ViewGroup viewGroup) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mercado-api-mobile.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Produto> call = jsonPlaceHolderApi.getProduto(idProduto);
        call.enqueue(new Callback<Produto>() {

            @Override
            public void onResponse(Call<Produto> call, retrofit2.Response<Produto> response) {
                if(!response.isSuccessful()) {
                    Log.e("deuruim", response.message());
                    return;
                }
                Log.i("deuboa", "ok");

                Produto produto = response.body();

                Bundle bundle = new Bundle();
                bundle.putParcelable("produto", produto);
                Carrinho.getInstancia().setProdutoDetalhe(produto);
                activity = getActivity();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();

                DetalheFragment fragment = DetalheFragment.newInstance(produto);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayoutProdutos, fragment);
                fragmentTransaction.commit();
                HorizontalScrollView view  =  activity.findViewById(R.id.horizontalScrollView);
                view.setVisibility(View.GONE);

//                GridLayoutManager glm = new GridLayoutManager(thisContext, 2);
//                RecyclerView rvProdutos = viewGroup.findViewById(R.id.rvProdutos);
//                rvProdutos.setLayoutManager(glm);
//                ProdutoAdapter produtoAdapter = new ProdutoAdapter(List<produto>, getActivity());
//                rvProdutos.setAdapter(produtoAdapter);
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                Log.e("deuruim", t.getMessage());
            }
        });
    }
}