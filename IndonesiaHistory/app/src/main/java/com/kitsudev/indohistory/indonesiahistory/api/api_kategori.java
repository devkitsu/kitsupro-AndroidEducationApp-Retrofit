package com.kitsudev.indohistory.indonesiahistory.api;

import com.kitsudev.indohistory.indonesiahistory.crud.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api_kategori {

        @FormUrlEncoded
        @POST("insert_kategori.php")
        Call<Value> kategori(@Field("nm_kategori") String nm_kategori);

        @GET("read_kategori.php")
        Call<Value> getkategori();

        @FormUrlEncoded
        @POST("delete_kategori.php")
        Call<Value> hapus(@Field("id_kategori") String id_kategori);
    }


