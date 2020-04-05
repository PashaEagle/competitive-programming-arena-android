package com.example.arena.activity.fragement.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arena.R;
import com.example.arena.activity.UserPageActivity;
import com.example.arena.singleton.UserSession;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalInfoFragment extends Fragment {

    private TextView tUserGlobalPageTotalSubmissions;
    private TextView tUserGlobalPageSubmissionsLastMonth;

    public GlobalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_global_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((UserPageActivity) getActivity())
                .setActionBarTitle(UserSession.currentUserUsername + " profile");

        tUserGlobalPageTotalSubmissions = view.findViewById(R.id.tUserGlobalPageTotalSubmissions);
        tUserGlobalPageSubmissionsLastMonth = view.findViewById(R.id.tUserGlobalPageSubmissionsLastMonth);

        tUserGlobalPageTotalSubmissions.append(UserSession.currentUser.getGlobalData().getTotalAmountOfSubmissions().toString());
        tUserGlobalPageSubmissionsLastMonth.append(UserSession.currentUser.getGlobalData().getSubmissionsLastMonth().toString());
    }
}
