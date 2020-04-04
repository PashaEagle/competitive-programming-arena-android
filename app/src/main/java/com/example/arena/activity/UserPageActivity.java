package com.example.arena.activity;

import android.os.Bundle;

import com.example.arena.R;
import com.example.arena.entity.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class UserPageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabGlobal, tabCodeforces, tabCodewars, tabHackerRank, tabHackerEarth;

    public PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//        tabLayout.setupWithViewPager(viewPager);
    }
}
