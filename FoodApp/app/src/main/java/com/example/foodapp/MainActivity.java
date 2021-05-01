package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private TextView name,mail;
    private BottomNavigationView bottomNavigationView;
    private ImageView menuImg;
    private NavigationView navigationView;
    private TextView profileText;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navView);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation);
        menuImg = findViewById(R.id.imageMenu);



        //navigationView.setItemIconTintList(null);

       navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(navViewListener);

        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navViewListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.bottom_nev_homeID:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.bottom_nev_categoriesID:
                            selectedFragment = new CategoriesFragment();
                            break;

                        case R.id.bottom_nev_cartID:
                            selectedFragment = new CartFragment();
                            break;

                        case R.id.bottom_nev_discoverID:
                            selectedFragment = new DiscoverFragment();
                            break;

                        case R.id.bottom_nev_favoriteID:
                            selectedFragment = new FavoritesFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment,selectedFragment).commit();
                    return true;
                }
            };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_profile:

                Intent intent = new Intent(this,Profile.class);
                startActivity(intent);
                break;


            case R.id.item_logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;

           /* case  R.id.item_resturent:

                Intent intent3 = new Intent(this,ResturantUpload.class);
                startActivity(intent3);
                break;*/

            case  R.id.item_about:

                Intent intent4 = new Intent(this,About.class);
                startActivity(intent4);

          /* case  R.id.item_add:

                Intent intent5 = new Intent(this,Add_items.class);
                startActivity(intent5);
                break;*/


        }
        return true;
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}