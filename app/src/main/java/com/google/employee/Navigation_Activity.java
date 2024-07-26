package com.google.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Navigation_Activity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle .syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,new AboutUS())
                    .commit();
            navigationView.setCheckedItem(R.id.optAbout);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();

               if (id ==R.id.optAbout)
               {
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.container,new AboutUS())
                           .commit();
               }
               else if(id==R.id.optContact)
               {
                   getSupportFragmentManager().beginTransaction()
                           .replace(R.id.container,new contactFragment())
                           .commit();

               }
               else if(id==R.id.optTask) {
                   Intent complete = new Intent(Navigation_Activity.this, Complete_Task_Activity.class);
                   startActivity(complete);
               }

               else if(id == R.id.optUpd){
                   Intent upd = new Intent(Navigation_Activity.this,Update_user.class);
                   startActivity(upd);
               }

               else if(id==R.id.optlog){
                   Intent intent = new Intent(Navigation_Activity.this,MainActivity.class);
                   startActivity(intent);
                   Toast.makeText(Navigation_Activity.this, "LogoutSuccessful", Toast.LENGTH_SHORT).show();
               }

               drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }

        });
    }
    @Override
    public  void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

}