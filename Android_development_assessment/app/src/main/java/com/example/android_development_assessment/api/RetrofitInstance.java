package com.example.android_development_assessment.api;

import android.util.Log;

import java.io.*;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    final static String BASE_URL = "https://api.apilayer.com/exchangerates_data/";


    public static OkHttpClient getRetrofitClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(60,TimeUnit.SECONDS);
        httpClient.writeTimeout(60,TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();
        return client;
    }

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getRetrofitClient())
                    .build();
        }
        return retrofit;
    }
}
