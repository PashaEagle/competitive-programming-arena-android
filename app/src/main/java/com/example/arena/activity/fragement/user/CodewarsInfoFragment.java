package com.example.arena.activity.fragement.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arena.R;
import com.example.arena.activity.UserPageActivity;
import com.example.arena.singleton.UserSession;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodewarsInfoFragment extends Fragment {

    private TextView tUserCodewarsPageUsername;
    private TextView tUserCodewarsPageRank;
    private TextView tUserCodewarsPageFullname;
    private TextView tUserCodewarsPageRating;
    private TextView tUserCodewarsPageHonor;
    private TextView tUserCodewarsPageSubmissionsCount;

    public CodewarsInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codewars_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((UserPageActivity) getActivity())
                .setActionBarTitle(UserSession.currentUserUsername + " profile");

        tUserCodewarsPageUsername = view.findViewById(R.id.tUserCodewarsPageUsername);
        tUserCodewarsPageRank = view.findViewById(R.id.tUserCodewarsPageRank);
        tUserCodewarsPageFullname = view.findViewById(R.id.tUserCodewarsPageFullname);
        tUserCodewarsPageRating = view.findViewById(R.id.tUserCodewarsPageRating);
        tUserCodewarsPageHonor = view.findViewById(R.id.tUserCodewarsPageHonor);
        tUserCodewarsPageSubmissionsCount = view.findViewById(R.id.tUserCodewarsPageSubmissionsCount);

        tUserCodewarsPageUsername.append(UserSession.currentUser.getCodeWarsUsername());
        tUserCodewarsPageRank.append(UserSession.currentUser.getCodeWarsData().getRankName());
        if (UserSession.currentUser.getCodeWarsData().getFullname() != null)
            tUserCodewarsPageFullname.append(UserSession.currentUser.getCodeWarsData().getFullname());
        if (UserSession.currentUser.getCodeWarsData().getRankScore() != null)
        tUserCodewarsPageRating.append(UserSession.currentUser.getCodeWarsData().getRankScore().toString());
        if (UserSession.currentUser.getCodeWarsData().getHonor() != null)
        tUserCodewarsPageHonor.append(UserSession.currentUser.getCodeWarsData().getHonor().toString());
        if (UserSession.currentUser.getCodeWarsData().getSubmissionsCount() != null)
        tUserCodewarsPageSubmissionsCount.append(UserSession.currentUser.getCodeWarsData().getSubmissionsCount().toString());
    }
}
