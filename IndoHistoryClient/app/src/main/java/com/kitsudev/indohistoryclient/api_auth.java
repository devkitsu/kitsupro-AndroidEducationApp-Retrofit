package com.kitsudev.indohistoryclient;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api_auth {

        @FormUrlEncoded
        @POST("login.php")
        Call<Value> login(@Field("nama") String nama,
                          @Field("password") String password);

        @FormUrlEncoded
        @POST("register.php")
        Call<Value> register(@Field("nama") String nama,
                             @Field("password") String password);


        @GET("logout.php")
        Call<Value> logout();
    }


