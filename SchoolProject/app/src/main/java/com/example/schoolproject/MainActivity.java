package com.example.schoolproject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Example School");

        drawerLayout = findViewById(R.id.drawer_layoutId);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

       /* FloatingActionButton fab = findViewById(R.id.fabId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show();*/


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_routine) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.routineFragment);
            getSupportActionBar().setTitle("Routine");

        }else if(item.getItemId()==R.id.nav_notice) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.noticeFragment);
            getSupportActionBar().setTitle("Notice");

        }else if(item.getItemId()==R.id.nav_onlineClasses) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.onlineClassesFragment);
            getSupportActionBar().setTitle("Online Classes");

        }else if(item.getItemId()==R.id.nav_gallery) {
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.galleryFragment);
            getSupportActionBar().setTitle("Gallery");

        }else if(item.getItemId()==R.id.nav_home){
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.homeFragment);
        }
        else if(item.getItemId()==R.id.nav_adminLogin){
            Navigation.findNavController(this,
                    R.id.nav_host_fragment).navigate(R.id.adminLoginFragment);
            getSupportActionBar().setTitle("Admin Panel");
        }

        drawerLayout.close();
        return true;
    }
}
