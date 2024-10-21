package com.example.ironworksgym.AgendamentoApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

public class Home2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.FrameLayout);

        // Receber os dados do agendamento via Intent
        Intent intent = getIntent();
        String equipamentoNome = intent.getStringExtra("equipamentoNome");
        String dataAgendamento = intent.getStringExtra("dataAgendamento");
        String horaAgendamento = intent.getStringExtra("horaAgendamento");

        // Passar os dados para o CalendarFragment usando Bundle
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle bundle = new Bundle();
        bundle.putString("equipamentoNome", equipamentoNome);
        bundle.putString("dataAgendamento", dataAgendamento);
        bundle.putString("horaAgendamento", horaAgendamento);
        calendarFragment.setArguments(bundle);

        // Carregar o fragmento CalendarFragment com os dados do agendamento
        loadFragment(calendarFragment, true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.settings) {
                    loadFragment(new SettingsFragment(), false);
                } else if (itemId == R.id.calendar) {
                    loadFragment(new CalendarFragment(), false);
                } else { // nav Profile
                    loadFragment(new ProfileFragment(), false);
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Se for a inicialização do aplicativo, substitua o fragmento atual
        if (isAppInitialized) {
            fragmentTransaction.replace(R.id.FrameLayout, fragment); // Use replace para não empilhar
        } else {
            fragmentTransaction.replace(R.id.FrameLayout, fragment);
        }

        fragmentTransaction.commit();
    }
}
