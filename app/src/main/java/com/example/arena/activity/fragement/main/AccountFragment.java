package com.example.arena.activity.fragement.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arena.activity.MainActivity;
import com.example.arena.R;
import com.example.arena.singleton.UserSession;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    private TextView tUsernameValue;
    private TextView tFullnameValue;
    private TextView tAgeValue;
    private TextView tGroupValue;
    private TextView tCodeForcesUsernameValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.account_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editAccountMainMenuButton:
                onButtonEditAccountClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity())
                .setActionBarTitle("My account");
        updateUI();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity())
                .setActionBarTitle("My account");

        tUsernameValue = view.findViewById(R.id.tUsernameValue);
        tFullnameValue = view.findViewById(R.id.tFullnameValue);
        tAgeValue = view.findViewById(R.id.tAgeValue);
        tGroupValue = view.findViewById(R.id.tGroupValue);
        tCodeForcesUsernameValue = view.findViewById(R.id.tCodeforcesUsernameValue);

        tUsernameValue.setText(UserSession.loggedUser.getUsername() + "");
        tFullnameValue.setText(UserSession.loggedUser.getFullName() + "");
        tAgeValue.setText(UserSession.loggedUser.getAge() + "");
        tGroupValue.setText(UserSession.loggedUser.getGroup() + "");
        tCodeForcesUsernameValue.setText(UserSession.loggedUser.getCodeForcesUsername());
    }

    public void updateUI() {

        tUsernameValue.setText(UserSession.loggedUser.getUsername());
        tFullnameValue.setText(UserSession.loggedUser.getFullName());
        tAgeValue.setText(UserSession.loggedUser.getAge().toString());
        tGroupValue.setText(UserSession.loggedUser.getGroup());
        tCodeForcesUsernameValue.setText(UserSession.loggedUser.getCodeForcesUsername());
    }

    public void onButtonEditAccountClick() {
        Intent intent = new Intent(".EditAccountActivity");
        startActivity(intent);
    }

}
