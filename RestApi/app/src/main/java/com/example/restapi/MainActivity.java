package com.example.restapi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.categoryRV);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);


        apiInterface.getposts().enqueue(new Callback<List<Postpojo>>() {
            @Override
            public void onResponse(Call<List<Postpojo>> call, Response<List<Postpojo>> response) {

                if (response.body().size() > 0) {

                    List<Postpojo> postpojoList = response.body();
                    AdapterClass adapterClass = new AdapterClass(postpojoList, MainActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapterClass);
                    Toast.makeText(MainActivity.this, "List is not empty", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Postpojo>> call, Throwable t) {

            }
        });
    }
}