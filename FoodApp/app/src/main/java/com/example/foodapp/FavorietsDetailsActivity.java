package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FavorietsDetailsActivity extends AppCompatActivity {


    Toolbar toolbar;
    String data,textUrl;
    WebView webView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_favoriets_details);

        toolbar = findViewById(R.id.favoritesDetailsTB);
        webView = findViewById(R.id.favoritesDetailsWV);
        imageView = findViewById(R.id.favoritesDetailsIV);
        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.favoritesCollapsingToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        data = getIntent().getStringExtra("name");

        if(data!=null){

            if(data.equals("Fried Chicken")){
                textUrl = "file:///android_asset/categoryFile/friedChicken.html";
                imageView.setImageResource(R.drawable.fried_chicken);
                collapsingToolbar.setTitle(""+data);
                webView.loadUrl(textUrl);
            }if(data.equals("Pasta")){
                textUrl = "file:///android_asset/categoryFile/pasta.html";
                imageView.setImageResource(R.drawable.pasta);
                collapsingToolbar.setTitle(""+data);
                webView.loadUrl(textUrl);
            }if(data.equals("Vegetarian")){
                textUrl = "file:///android_asset/categoryFile/vegetarian.html";
                imageView.setImageResource(R.drawable.vegetarian);
                collapsingToolbar.setTitle(""+data);
                webView.loadUrl(textUrl);
            }if(data.equals("Dessert")){
                textUrl = "file:///android_asset/categoryFile/dessert.html";
                imageView.setImageResource(R.drawable.dessert);
                collapsingToolbar.setTitle(""+data);
                webView.loadUrl(textUrl);
            }if(data.equals("Chicken curry")){
                textUrl = "file:///android_asset/categoryFile/chickenCurry.html";
                imageView.setImageResource(R.drawable.chicken_curry);
                collapsingToolbar.setTitle(""+data);
                webView.loadUrl(textUrl);
            }if(data.equals("Chicken Soup")){
                textUrl = "file:///android_asset/categoryFile/chickenSoup.html";
                imageView.setImageResource(R.drawable.chicken_soup);
                collapsingToolbar.setTitle(""+data);
                webView.loadUrl(textUrl);
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
}