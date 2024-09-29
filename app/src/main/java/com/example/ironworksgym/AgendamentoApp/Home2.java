package com.example.ironworksgym.AgendamentoApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ironworksgym.Fragment.CalendarFragment;
import com.example.ironworksgym.Fragment.HomeFragment;
import com.example.ironworksgym.Fragment.ProfileFragment;
import com.example.ironworksgym.Fragment.SettingsFragment;
import com.example.ironworksgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Home2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste1);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.FrameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.home){
                    loadFragment(new HomeFragment(), false);

                } else if (itemId == R.id.settings){
                    loadFragment(new SettingsFragment(), false);

                } else if (itemId == R.id.calendar){
                    loadFragment(new CalendarFragment(), false);

                } else { //nav Profile
                    loadFragment(new ProfileFragment(), false);
                }
                loadFragment(new HomeFragment(), true);

                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.FrameLayout, fragment);

        }else{
            fragmentTransaction.replace(R.id.FrameLayout, fragment);
        }

        fragmentTransaction.commit();

    }
}
