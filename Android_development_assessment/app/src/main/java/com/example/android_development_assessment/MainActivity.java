package com.example.android_development_assessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.android_development_assessment.viewmodel.SymbolViewModel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private SymbolViewModel symbolViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        symbolViewModel = new ViewModelProvider(this).get(SymbolViewModel.class);
        symbolViewModel.getsymbols();
    }
}