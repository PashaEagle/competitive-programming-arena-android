package com.example.arena.fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        tUsernameValue = view.findViewById(R.id.tUsernameValue);
        tFullnameValue = view.findViewById(R.id.tFullnameValue);
        tAgeValue = view.findViewById(R.id.tAgeValue);
        tGroupValue = view.findViewById(R.id.tGroupValue);

        tUsernameValue.setText(UserSession.loggedUser.getUsername()+"");
        tFullnameValue.setText(UserSession.loggedUser.getFullName()+"");
        tAgeValue.setText(UserSession.loggedUser.getAge()+"");
        tGroupValue.setText(UserSession.loggedUser.getGroup()+"");
    }
}
