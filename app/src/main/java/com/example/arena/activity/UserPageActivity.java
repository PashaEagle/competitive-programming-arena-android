package com.example.arena.activity;

import android.os.Bundle;

import com.example.arena.R;
import com.example.arena.entity.PageAdapter;
import com.example.arena.integration.CoreCommunicationService;
import com.example.arena.singleton.UserSession;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class UserPageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabGlobal, tabCodeforces, tabCodewars, tabHackerRank, tabHackerEarth;
    private CoreCommunicationService coreCommunicationService;

    public PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.coreCommunicationService = new CoreCommunicationService(this);
        setContentView(R.layout.activity_user_page);

        tabLayout = findViewById(R.id.userPageTabLayout);

        tabGlobal = findViewById(R.id.userPageTabGlobal);
        tabCodeforces = findViewById(R.id.userPageTabCodeforces);
        tabCodewars = findViewById(R.id.userPageTabCodewars);
        tabHackerRank = findViewById(R.id.userPageTabHackerrank);
        tabHackerEarth = findViewById(R.id.userPageTabHackerEarth);

        viewPager = findViewById(R.id.userPageViewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        UserSession.currentUser = coreCommunicationService.getCodeforcesUserData(UserSession.currentUserUsername);
        System.out.println("CONVERTED TIME = " + LocalDateTime.ofInstant(Instant.ofEpochSecond(1586070657L),
                ZoneId.systemDefault()));
        System.out.println();
//        tabLayout.setupWithViewPager(viewPager);
    }
}
