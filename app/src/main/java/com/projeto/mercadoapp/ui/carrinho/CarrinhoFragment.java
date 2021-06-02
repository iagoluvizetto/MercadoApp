package com.projeto.mercadoapp.ui.carrinho;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.adapter.CarrinhoAdapter;
import com.projeto.mercadoapp.adapter.ProdutoAdapter;
import com.projeto.mercadoapp.models.JsonPlaceHolderApi;
import com.projeto.mercadoapp.models.Produto;
import com.projeto.mercadoapp.ui.home.ProdutosFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarrinhoFragment extends Fragment {

    private com.projeto.mercadoapp.ui.carrinho.CarrinhoViewModel CarrinhoViewModel;

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";
        private View view;
        private Context thisContext;

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public CarrinhoFragment() {
            // Required empty public constructor
        }

        // TODO: Rename and change types and number of parameters
        public static com.projeto.mercadoapp.ui.carrinho.CarrinhoFragment newInstance(String param1, String param2) {
            com.projeto.mercadoapp.ui.carrinho.CarrinhoFragment fragment = new com.projeto.mercadoapp.ui.carrinho.CarrinhoFragment();
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
            //String category = bundle.getString("category");
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_carrinho, container, false);
            thisContext = container.getContext();

            //TextView textView = view.findViewById(R.id.produtosFragmentTitle);

            //textView.setText(msg);

   //         callJson("hortifruti", container);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                   RecyclerView rvProdutos = view.findViewById(R.id.rv_carrinho);
                    rvProdutos.setLayoutManager(llm);
                    CarrinhoAdapter carrinhoAdapter = new CarrinhoAdapter(getActivity());
                    rvProdutos.setAdapter(carrinhoAdapter);
            return view;

        }

//        public void callJson(String category, ViewGroup viewGroup) {
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("https://mercado-api-mobile.herokuapp.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//
//            Call<List<Produto>> call = jsonPlaceHolderApi.getProdutosByCategory(category);
//            call.enqueue(new Callback<List<Produto>>() {
//
//                @Override
//                public void onResponse(Call<List<Produto>> call, retrofit2.Response<List<Produto>> response) {
//                    if(!response.isSuccessful()) {
//                        Log.e("deuruim", response.message());
//                        return;
//                    }
//                    Log.i("deuboa", "ok");
//
//                    List<Produto> produtos = response.body();
//
//                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                    RecyclerView rvProdutos = viewGroup.findViewById(R.id.rv_carrinho);
//                    rvProdutos.setLayoutManager(llm);
//                    CarrinhoAdapter carrinhoAdapter = new CarrinhoAdapter();
//                    rvProdutos.setAdapter(carrinhoAdapter);
//
//                }
//
//                @Override
//                public void onFailure(Call<List<Produto>> call, Throwable t) {
//                    Log.e("deuruim", t.getMessage());
//                }
//            });
//        }
  }