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
import com.example.arena.activity.MainActivity;
import com.example.arena.activity.UserPageActivity;
import com.example.arena.singleton.UserSession;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeforcesInfoFragment extends Fragment {

    private TextView tUserCodeforcesPageUsername;
    private TextView tUserCodeforcesPageRank;
    private TextView tUserCodeforcesPageMaxRank;
    private TextView tUserCodeforcesPageRating;
    private TextView tUserCodeforcesPageMaxRating;
    private TextView tUserCodeforcesPageRegisteredAt;
    private TextView tUserCodeforcesPageLastOnlineAt;
    private TextView tUserCodeforcesPageSubmissionsCount;

    public CodeforcesInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codeforces_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((UserPageActivity) getActivity())
                .setActionBarTitle(UserSession.currentUserUsername + " profile");

        tUserCodeforcesPageUsername = view.findViewById(R.id.tUserCodeforcesPageUsername);
        tUserCodeforcesPageRank = view.findViewById(R.id.tUserCodeforcesPageRank);
        tUserCodeforcesPageMaxRank = view.findViewById(R.id.tUserCodeforcesPageMaxRank);
        tUserCodeforcesPageRating = view.findViewById(R.id.tUserCodeforcesPageRating);
        tUserCodeforcesPageMaxRating = view.findViewById(R.id.tUserCodeforcesPageMaxRating);
        tUserCodeforcesPageRegisteredAt = view.findViewById(R.id.tUserCodeforcesPageRegisteredAt);
        tUserCodeforcesPageLastOnlineAt = view.findViewById(R.id.tUserCodeforcesPageLastOnlineAt);
        tUserCodeforcesPageSubmissionsCount = view.findViewById(R.id.tUserCodeforcesPageSubmissionsCount);

        tUserCodeforcesPageUsername.append(UserSession.currentUser.getCodeForcesUsername());
        tUserCodeforcesPageRank.append(UserSession.currentUser.getCodeForcesData().getRank());
        tUserCodeforcesPageMaxRank.append(UserSession.currentUser.getCodeForcesData().getMaxRank());
        tUserCodeforcesPageRating.append(UserSession.currentUser.getCodeForcesData().getRating().toString());
        tUserCodeforcesPageMaxRating.append(UserSession.currentUser.getCodeForcesData().getMaxRating().toString());
        tUserCodeforcesPageRegisteredAt.append(LocalDateTime.ofInstant(Instant.ofEpochSecond(UserSession.currentUser.getCodeForcesData().getRegisteredAt()),
                ZoneId.systemDefault()).toString());
        tUserCodeforcesPageLastOnlineAt.append(LocalDateTime.ofInstant(Instant.ofEpochSecond(UserSession.currentUser.getCodeForcesData().getLastOnlineAt()),
                ZoneId.systemDefault()).toString());
        tUserCodeforcesPageSubmissionsCount.append(UserSession.currentUser.getCodeForcesData().getSubmissionsCount().toString());
    }
}
