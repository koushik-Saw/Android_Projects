package com.example.android_development_assessment.api;

import java.io.*;

import okhttp3.*;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    final static String BASE_URL = "https://api.apilayer.com/exchangerates_data/";


    /*public static OkHttpClient getRetrofitClient() throws IOException {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Request.Builder requestBuilder = new Request.Builder()
                .addHeader("apikey", "etRvyJdLBoYUigEULDDDrPmVfcfq0V41");

        Request request = requestBuilder.build();

        OkHttpClient client = httpClient.build();
        Response response = client.newCall(request).execute();
        return client;
    }*/

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
