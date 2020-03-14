package com.kitsudev.indohistory.indonesiahistory.api;

import com.kitsudev.indohistory.indonesiahistory.crud.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api_kuis {

        @FormUrlEncoded
        @POST("insert_kuis.php")
        Call<Value> kuis(@Field("soal") String soal,
                         @Field("jawaban") String jawaban,
                           @Field("optA") String optA,
                           @Field("optB") String optB,
                           @Field("optC") String optC,
                           @Field("optD") String optD,
                           @Field("id_kategori") String id_kategori,
                           @Field("level") String level);

        @GET("read_data.php")
        Call<Value> getkuis();

        @FormUrlEncoded
        @POST("delete_kuis.php")
        Call<Value> hapus(@Field("id_kuis") String id_kuis);

        @FormUrlEncoded
        @POST("ubah_kuis.php")
        Call<Value> ubah(@Field("id_kuis") String id_kuis,
                         @Field("soal") String soal,
                         @Field("jawaban") String jawaban,
                         @Field("optA") String optA,
                         @Field("optB") String optB,
                         @Field("optC") String optC,
                         @Field("optD") String optD,
                         @Field("level") String level);

        @FormUrlEncoded
        @POST("insert_gambar.php")
        Call<Value> gambar(@Field("gambar") String gambar,
                           @Field("nama") String nama);

        @GET("read_gambar.php")
        Call<Value> getgambar();

        @GET("read_skor.php")
        Call<Value> getskor();

        @GET("read_user.php")
        Call<Value> getuser();

        @FormUrlEncoded
        @POST("delete_gambar.php")
        Call<Value> hapusgambar(@Field("id_gambar") String id_kuis);
    }


