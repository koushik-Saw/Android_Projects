package com.example.android_development_assessment.api;

import com.example.android_development_assessment.model.SymbolsModel;

import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Headers;

public interface ApiInterface {

    @GET("symbols")
    @Headers("apikey: vAc6t2qHmPugaumCvdqkTWg7BQPmGfSu")
    Call<SymbolsModel> getSymbolList();
}
