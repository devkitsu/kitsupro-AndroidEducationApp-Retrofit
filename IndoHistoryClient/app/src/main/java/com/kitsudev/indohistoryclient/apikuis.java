package com.kitsudev.indohistoryclient;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apikuis {
        @GET("read_materi.php")
        Call<Value> getmateri();

    @FormUrlEncoded
    @POST("read_kuis.php")
    Call<Value> getsoal(@Field("levelQ") String levelQ);

    @FormUrlEncoded
    @POST("skor_mudah.php")
    Call<Value> getskormudah(@Field("nama") String nama);

    @FormUrlEncoded
    @POST("skor_sedang.php")
    Call<Value> getskorsedang(@Field("nama") String nama);

    @FormUrlEncoded
    @POST("skor_sulit.php")
    Call<Value> getskorsulit(@Field("nama") String nama);

    @GET("read_skor.php")
    Call<Value> getskor();

    @GET("soal_mudah.php")
    Call<Value> getsoalmudah();

    @GET("soal_sedang.php")
    Call<Value> getsoalsedang();

    @GET("soal_sulit.php")
    Call<Value> getsoalsulit();

    @FormUrlEncoded
    @POST("skor.php")
    Call<Value> skor(@Field("nama") String nama,
                      @Field("score") String score,
                     @Field("levelQ") String levelQ);
    }


