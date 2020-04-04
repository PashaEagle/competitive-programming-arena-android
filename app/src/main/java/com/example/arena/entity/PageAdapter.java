package com.example.arena.entity;

import com.example.arena.activity.fragement.user.CodeforcesInfoFragment;
import com.example.arena.activity.fragement.user.CodewarsInfoFragment;
import com.example.arena.activity.fragement.user.GlobalInfoFragment;
import com.example.arena.activity.fragement.user.HackerearthInfoFragment;
import com.example.arena.activity.fragement.user.HackerrankInfoFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public PageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GlobalInfoFragment();

            case 1:
                return new CodeforcesInfoFragment();

            case 2:
                return new CodewarsInfoFragment();

            case 3:
                return new HackerrankInfoFragment();

            case 4:
                return new HackerearthInfoFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
