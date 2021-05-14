package com.projeto.mercadoapp.ui.home;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.projeto.mercadoapp.models.JsonPlaceHolderApi;
import com.projeto.mercadoapp.models.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;


    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Home");
    }

    public MutableLiveData<String> getText() { return mText; }


}