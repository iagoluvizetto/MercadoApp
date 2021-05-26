package com.projeto.mercadoapp.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.projeto.mercadoapp.R;
import com.projeto.mercadoapp.adapter.ProdutoAdapter;
import com.projeto.mercadoapp.models.JsonPlaceHolderApi;
import com.projeto.mercadoapp.models.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProdutosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProdutosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private Context thisContext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProdutosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProdutosFragment.
     */
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_produtos, container, false);
        thisContext = container.getContext();

        //TextView textView = view.findViewById(R.id.produtosFragmentTitle);

        //textView.setText(msg);

        callJson(category, container);

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
//                LinearLayout linearLayout = view.findViewById(R.id.listagemProdutos);
//                for(Produto p : produtos){
//                    TextView textView = new TextView(view.getContext());
//                    ImageView imageView = new ImageView(view.getContext());
//                    textView.setText(p.getNome());
//                    Glide.with(view.getContext()).load("https://" + p.getImg()).into(imageView);
//                    imageView.setAdjustViewBounds(true);
//                    imageView.setMaxHeight(200);
//                    imageView.setMaxWidth(200);
//                    linearLayout.addView(textView);
//                    linearLayout.addView(imageView);
//
//                }

            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Log.e("deuruim", t.getMessage());
            }
        });
    }
}