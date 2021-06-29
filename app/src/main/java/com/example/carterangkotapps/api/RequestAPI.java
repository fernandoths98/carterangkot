package com.example.carterangkotapps.api;

import com.example.carterangkotapps.model.JsonResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestAPI {
    @GET("view.php")
    Call<JsonResponse> view();

    @GET("update.php")
    Call<JsonResponse> update();

    @GET("register.php")
    Call<JsonResponse> akun(@Field( "name" )String name);

    @FormUrlEncoded
    @POST("search.php")
    Call<JsonResponse> search(@Field( "search" )String search);

    @FormUrlEncoded
    @POST("register.php")
    Call<JsonResponse> create (@Field( "name" )String name,
                               @Field( "password" )String password,
                               @Field( "email" )String email);

    @FormUrlEncoded
    @POST("login.php")
    Call<JsonResponse> login (@Field( "email" )String email,
                              @Field( "password" )String password);
}
