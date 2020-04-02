package com.example.arena.activity.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arena.R;
import com.example.arena.activity.MainActivity;
import com.example.arena.entity.UserItem;
import com.example.arena.entity.UserRankingAdapter;
import com.example.arena.singleton.UserSession;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RankFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserItem> userRankingList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rank, container, false);

        userRankingList = UserSession.allUsers;

        recyclerView = rootView.findViewById(R.id.rankingRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new UserRankingAdapter(userRankingList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Ranking");
        UserSession.allUsers.add(new UserItem(R.drawable.ic_rank, "Username7", "Some other item"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_account_info_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
