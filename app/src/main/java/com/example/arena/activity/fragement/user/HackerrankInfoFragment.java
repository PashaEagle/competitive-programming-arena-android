package com.example.arena.activity.fragement.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arena.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HackerrankInfoFragment extends Fragment {

    public HackerrankInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hackerrank_info, container, false);
    }
}
