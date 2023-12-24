package com.darkline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.darkline.R;
import com.darkline.fragment.HomeFragment;
import com.darkline.fragment.ProfileFragment;
import com.darkline.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class  MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        Bundle bundle = new Bundle();
        bundle.putString("LoginKey",intent.getStringExtra("LOGINKEY"));

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView bnView = findViewById(R.id.bottomNavigation);



//Bottom Navigation
        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home_page_search: {
                        loadFrag(new SearchFragment(),false);
                        Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.home_page_profile:{
                        loadFrag(new ProfileFragment(),false);
                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:{
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);
                        loadFrag(homeFragment,false);
//
                    }
                }
                return true;
            }
        });
        bnView.setSelectedItemId(R.id.home_page_home);
// Bootom Navigation End

    }

    public void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag)
            ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container,fragment);

        ft.commit();
    }
}



