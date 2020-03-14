package com.kitsudev.indohistory.indonesiahistory.api;

import com.kitsudev.indohistory.indonesiahistory.crud.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api_auth {

        @FormUrlEncoded
        @POST("login.php")
        Call<Value> login(@Field("username") String username,
                          @Field("password") String password);

        @FormUrlEncoded
        @POST("register.php")
        Call<Value> register(@Field("username") String username,
                             @Field("password") String password,
                             @Field("nama") String nama);


        @GET("logout.php")
        Call<Value> logout();
    }


