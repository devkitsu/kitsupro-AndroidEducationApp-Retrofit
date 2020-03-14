package com.kitsudev.indohistory.indonesiahistory.laporan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.api.api_kuis;
import com.kitsudev.indohistory.indonesiahistory.crud.Result;
import com.kitsudev.indohistory.indonesiahistory.crud.Value;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class laporan_skor extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/aldo/kuis/";
    private List<Result> results = new ArrayList<>();
    private RecyclerViewAdapter_Skor viewAdapter;
    @BindView(R.id.recyclerView_barang)
    RecyclerView recyclerView;
    @BindView(R.id.progresbar_barang)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_skor);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Laporan Skor User");

        viewAdapter = new RecyclerViewAdapter_Skor(this,results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        loadMat();
    }

    protected void onResume() {
        super.onResume();
        loadMat();
    }

    private void loadMat(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_kuis api = retrofit.create(api_kuis.class);
        Call<Value> call = api.getskor();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                if (value.equals("1")) {
                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter_Skor(laporan_skor.this, results);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }
}
