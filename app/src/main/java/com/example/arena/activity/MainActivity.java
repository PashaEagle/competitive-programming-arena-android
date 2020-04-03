package com.example.arena.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arena.R;
import com.example.arena.activity.fragement.AccountFragment;
import com.example.arena.activity.fragement.RankFragment;
import com.example.arena.activity.fragement.SettingsFragment;
import com.example.arena.dto.user.UserDto;
import com.example.arena.integration.CoreCommunicationService;
import com.example.arena.singleton.UserSession;
import com.example.arena.transformer.UserTransformer;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private CoreCommunicationService coreCommunicationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        coreCommunicationService = new CoreCommunicationService(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_account);
        }

        UserSession.allUsers = coreCommunicationService.getAllUsersRequest();
        UserSession.allUserItems = new ArrayList<>();

        for (int i = 0; i < UserSession.allUsers.size(); ++i) {
            UserDto userDto = UserSession.allUsers.get(i);
            UserSession.allUserItems.add(UserTransformer.fromUserDtoToUserItem(userDto, i+1, userDto.getAge().toString()));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                break;

            case R.id.nav_rank:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RankFragment()).commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;

            case R.id.nav_info:
                Toast.makeText(this, "By Pasha Kolesnyk", Toast.LENGTH_SHORT);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
