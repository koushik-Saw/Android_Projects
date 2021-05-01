package com.example.collageproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_routine) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.routineFragment);
           // getSupportActionBar().setTitle("Routine");

        } else if (item.getItemId() == R.id.nav_notice) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.noticeFragment);
           // getSupportActionBar().setTitle("Notice");

        } else if (item.getItemId() == R.id.nav_onlineClasses) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.onlineClassesFragment);
           // getSupportActionBar().setTitle("Online Classes");

        } else if (item.getItemId() == R.id.nav_gallery) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.galleryFragment);
           // getSupportActionBar().setTitle("Gallery");

        } else if (item.getItemId() == R.id.nav_home) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.homeFragment);
        } else if (item.getItemId() == R.id.nav_result) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.resultFragment);
        } else if (item.getItemId() == R.id.nav_website) {
           Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.websiteFragment);

            /*Intent intent = new Intent(MainActivity.this,Add.class);
            startActivity(intent);*/

        }/*else if (item.getItemId() == R.id.nav_add_routine) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.add_routinefragment);
        } else if (item.getItemId() == R.id.nav_addNotice) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.add_noticeFragment);
        }else if (item.getItemId() == R.id.nav_addOnlineclass) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.AddonlineClassesfragment);
        }else if (item.getItemId() == R.id.nav_addgallery) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.addGallerykFragment);
        }*/

        drawerLayout.close();
        return true;
    }
}