package com.example.android_development_assessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.android_development_assessment.api.ApiInterface;
import com.example.android_development_assessment.api.RetrofitInstance;
import com.example.android_development_assessment.model.SymbolsModel;
import com.example.android_development_assessment.viewmodel.SymbolViewModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private SymbolViewModel symbolViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        symbolViewModel = new ViewModelProvider(this).get(SymbolViewModel.class);
        symbolViewModel.getsymbols().observe(this, new Observer<List<SymbolsModel.Symbols>>() {
            @Override
            public void onChanged(List<SymbolsModel.Symbols> symbols) {
                for(int i=0;i<symbols.size();i++){
                    Log.e("TAG", "onChanged: "+symbols.get(i).getAed() );
                }

            }
        });


    }
}