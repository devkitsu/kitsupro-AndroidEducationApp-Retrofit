package com.kitsudev.indohistory.indonesiahistory.api;

import com.kitsudev.indohistory.indonesiahistory.crud.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api_materi {

        @FormUrlEncoded
        @POST("insert_materi.php")
        Call<Value> materi(@Field("nm_materi") String nm_materi,
                           @Field("id_kategori") String id_kategori,
                             @Field("isi") String isi,
                             @Field("gambar") String gambar);

        @GET("read_materi.php")
        Call<Value> getmateri();

        @FormUrlEncoded
        @POST("delete_materi.php")
        Call<Value> hapus(@Field("id_materi") String id_materi);

        @FormUrlEncoded
        @POST("ubah_materi.php")
        Call<Value> ubah(@Field("nm_materi") String nm_materi,
                           @Field("id_kategori") String id_kategori,
                           @Field("isi") String isi,
                           @Field("id_materi") String id_materi);
    }


