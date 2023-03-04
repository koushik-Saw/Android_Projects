package com.example.android_development_assessment.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.android_development_assessment.api.ApiInterface;
import com.example.android_development_assessment.api.RetrofitInstance;
import com.example.android_development_assessment.model.SymbolsModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SymbolsRepo {
    private Context mContext;
    private MutableLiveData mutableLiveData;

    private SymbolsModel symbolsList;

    public SymbolsRepo(Context context) {
        this.mContext = context;
    }

    public MutableLiveData<SymbolsModel> getsymbols() {

        try {
            mutableLiveData = new MutableLiveData();

            ApiInterface apiServices = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);

            apiServices.getSymbolList().enqueue(new Callback<SymbolsModel>() {
                @Override
                public void onResponse(Call<SymbolsModel> call, Response<SymbolsModel> response) {
                    String s = response.body().toString();
                    System.out.println(s);
                    Log.e("rankList", "onFailure: "+s);
                }

                @Override
                public void onFailure(Call<SymbolsModel> call, Throwable t) {
                    Log.e("rankList", "onFailure: "+t.getLocalizedMessage());
                }
            });

        } catch (Exception e){}
        return mutableLiveData;
    }
}
