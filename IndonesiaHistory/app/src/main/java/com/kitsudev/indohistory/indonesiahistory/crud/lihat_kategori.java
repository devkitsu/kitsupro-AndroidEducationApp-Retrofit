package com.kitsudev.indohistory.indonesiahistory.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.adapter.RecyclerViewAdapter_Kategori;
import com.kitsudev.indohistory.indonesiahistory.api.api_kategori;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lihat_kategori extends AppCompatActivity {
    public static final String URL = "http://kitsudev.000webhostapp.com/aldo/kategori/";
    private List<Result> results = new ArrayList<>();
    private RecyclerViewAdapter_Kategori viewAdapter;
    @BindView(R.id.recyclerView_barang) RecyclerView recyclerView;
    @BindView(R.id.progresbar_barang)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kategori);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Data Kategori");

        viewAdapter = new RecyclerViewAdapter_Kategori(this,results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        loadKat();
    }

    protected void onResume() {
        super.onResume();
        loadKat();
    }

    private void loadKat(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_kategori api = retrofit.create(api_kategori.class);
        Call<Value> call = api.getkategori();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                if (value.equals("1")) {
                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter_Kategori(lihat_kategori.this, results);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}
