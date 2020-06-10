package com.example.arena.activity.fragement.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.arena.R;
import com.example.arena.activity.LoginActivity;
import com.example.arena.activity.MainActivity;
import com.example.arena.dto.user.UserDto;
import com.example.arena.entity.UserItem;
import com.example.arena.entity.UserRankingAdapter;
import com.example.arena.singleton.UserSession;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RankFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserRankingAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rank, container, false);

        recyclerView = rootView.findViewById(R.id.rankingRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new UserRankingAdapter(UserSession.allUserItems);
        spinner = rootView.findViewById(R.id.spinner);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //TODO
        adapter.setOnUserItemClickListener(new UserRankingAdapter.OnUserItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UserDto currentUser = UserSession.allUsers.get(position);
                UserSession.currentUserUsername = currentUser.getUsername();
                Intent intent = new Intent(".UserPageActivity");
                startActivity(intent);
                Toast.makeText(getActivity(), "Page for " + currentUser.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 0:
                        Collections.sort(UserSession.allUserItems, UserItem.BY_TOTAL_SUBMISSIONS);
                        Collections.sort(UserSession.allUsers, UserDto.BY_TOTAL_SUBMISSIONS);
                        break;
                    case 1:
                        Collections.sort(UserSession.allUserItems, UserItem.BY_SUBMISSIONS_THIS_MONTH);
                        Collections.sort(UserSession.allUsers, UserDto.BY_SUBMISSIONS_THIS_MONTH);
                        break;
                    default:
                        System.out.println("Not found sort item, position = " + position);
                        break;
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Ranking");
//        UserSession.allUsers.add(new UserItem(R.drawable.ic_rank, "Username7", "Some other item"));
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_account_info_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
