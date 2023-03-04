package com.example.android_development_assessment.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.android_development_assessment.api.ApiInterface;
import com.example.android_development_assessment.api.RetrofitInstance;
import com.example.android_development_assessment.model.SymbolsModel;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SymbolsRepo {
    private Context mContext;
    private MutableLiveData mutableLiveData;

    private SymbolsModel symbolsModel;

    public SymbolsRepo(Context context) {
        this.mContext = context;
    }

    public MutableLiveData<List<SymbolsModel.Symbols>> getsymbols() {

        try {
            JSONObject symbolsObject = new JSONObject().getJSONObject("symbols");
            JSONArray symbolsArray = symbolsObject.toJSONArray(symbolsObject.names());
            mutableLiveData = new MutableLiveData();

            ApiInterface apiServices = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
            apiServices.getSymbolList().enqueue(new Callback<SymbolsModel>() {
                @Override
                public void onResponse(Call<SymbolsModel> call, Response<SymbolsModel> response) {
                    if(response.isSuccessful()){
                        symbolsModel = response.body();

                    }else {
                        Log.e("failed", "onFailure: error" );
                    }
                }

                @Override
                public void onFailure(Call<SymbolsModel> call, Throwable t) {
                    Log.e("failed", "onFailure: "+t.getLocalizedMessage() );
                }
            });

        } catch (Exception e){}
        return mutableLiveData;
    }
}
