package com.kitsudev.indohistory.indonesiahistory.crud;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.adapter.RecyclerViewAdapter_Gambar;
import com.kitsudev.indohistory.indonesiahistory.api.api_kuis;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class lihat_kuisgambar extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/sejarah/tebak_gambar/";
    private List<Result> results = new ArrayList<>();
    private RecyclerViewAdapter_Gambar viewAdapter;
    @BindView(R.id.recyclerView_barang)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kuisgambar);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Data Materi");
        viewAdapter = new RecyclerViewAdapter_Gambar(this,results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        load();
    }

    private void load(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_kuis api = retrofit.create(api_kuis.class);
        Call<Value> call = api.getgambar();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter_Gambar(lihat_kuisgambar.this, results);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }
}
