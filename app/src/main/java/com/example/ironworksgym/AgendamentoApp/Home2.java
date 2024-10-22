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
import com.example.ironworksgym.Models.Usuario;
import com.example.ironworksgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class Home2 extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private Usuario usuario;

    private static final String TAG_HOME = "home_fragment";
    private static final String TAG_SETTINGS = "settings_fragment";
    private static final String TAG_CALENDAR = "calendar_fragment";
    private static final String TAG_PROFILE = "profile_fragment";
    private String currentTag = TAG_HOME;

    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.framelayout);
        usuario = getIntent().getParcelableExtra("usuario");

        // Inicializa o mapa de fragmentos
        initializeFragmentMap();

        // Receber os dados do agendamento via Intent
        Intent intent = getIntent();
        String equipamentoNome = intent.getStringExtra("equipamentoNome");
        String dataAgendamento = intent.getStringExtra("dataAgendamento");
        String horaAgendamento = intent.getStringExtra("horaAgendamento");

        // Passar os dados para o CalendarFragment usando Bundle, mas não carregá-lo automaticamente
        if (equipamentoNome != null || dataAgendamento != null || horaAgendamento != null) {
            CalendarFragment calendarFragment = new CalendarFragment();
            Bundle bundle = new Bundle();
            bundle.putString("equipamentoNome", equipamentoNome);
            bundle.putString("dataAgendamento", dataAgendamento);
            bundle.putString("horaAgendamento", horaAgendamento);
            calendarFragment.setArguments(bundle);

            // Armazena o fragmento de calendário com as informações, mas não o carrega ainda.
            fragmentMap.put(R.id.calendar, calendarFragment);
        }

        // Carregar o HomeFragment como tela inicial
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), TAG_HOME, false);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Verifica se o ID do item selecionado está no mapa de fragmentos
                Fragment selectedFragment = fragmentMap.get(item.getItemId());

                // Se o fragmento for o ProfileFragment, passar o objeto Usuario
                if (selectedFragment instanceof ProfileFragment) {
                    Bundle userBundle = new Bundle();
                    userBundle.putParcelable("usuario", usuario);
                    selectedFragment.setArguments(userBundle);
                }

                if (selectedFragment != null) {
                    // Carregar o fragmento selecionado
                    loadFragment(selectedFragment, getTagForFragment(selectedFragment), false);
                }

                return true;
            }
        });
    }

    private void initializeFragmentMap() {
        // Mapeia os IDs de menu para os fragmentos correspondentes
        fragmentMap.put(R.id.home, new HomeFragment());
        fragmentMap.put(R.id.settings, new SettingsFragment());
        fragmentMap.put(R.id.calendar, new CalendarFragment()); // Inicializa, mas não carrega ainda
        fragmentMap.put(R.id.profile, new ProfileFragment());
    }

    private String getTagForFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            return TAG_HOME;
        } else if (fragment instanceof SettingsFragment) {
            return TAG_SETTINGS;
        } else if (fragment instanceof CalendarFragment) {
            return TAG_CALENDAR;
        } else if (fragment instanceof ProfileFragment) {
            return TAG_PROFILE;
        }
        return TAG_HOME; // Default case
    }

    private void loadFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);

        // Se o fragmento já existe, mostre-o. Caso contrário, adicione-o.
        if (existingFragment != null) {
            fragmentTransaction.replace(R.id.framelayout, existingFragment, tag);
        } else {
            fragmentTransaction.replace(R.id.framelayout, fragment, tag);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag);
            }
        }

        fragmentTransaction.commit();
        currentTag = tag;  // Atualiza a tag do fragmento atual
    }

    @Override
    public void onBackPressed() {
        // Se houver fragmentos na pilha de retorno, vai para o fragmento anterior.
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();  // Comportamento padrão
        }
    }
}
