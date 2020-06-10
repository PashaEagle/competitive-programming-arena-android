package com.example.arena.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arena.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserRankingAdapter extends RecyclerView.Adapter<UserRankingAdapter.UserRankingViewHolder> {

    private ArrayList<UserItem> mUserList;
    private OnUserItemClickListener userItemClickListener;

    public interface OnUserItemClickListener {
        void onItemClick(int position);
    }

    public void setOnUserItemClickListener(OnUserItemClickListener listener) {
        this.userItemClickListener = listener;
    }

    public static class UserRankingViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mPlace;
        public TextView mUsername;
        public TextView mLastSeenTime;
        public TextView mGroup;
        public TextView mValue;

        public UserRankingViewHolder(@NonNull View itemView, final OnUserItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.rankingUserItemImageView);
            mPlace = itemView.findViewById(R.id.rankingUserItemPlace);
            mUsername = itemView.findViewById(R.id.rankingUserItemUsername);
            mLastSeenTime = itemView.findViewById(R.id.rankingUserItemLastSeenTime);
            mGroup = itemView.findViewById(R.id.rankingUserItemGroup);
            mValue = itemView.findViewById(R.id.rankingUserItemValue);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public UserRankingAdapter(ArrayList<UserItem> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public UserRankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_user_item, parent, false);
        UserRankingViewHolder urvh = new UserRankingViewHolder(v, userItemClickListener);
        return urvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRankingViewHolder holder, int position) {
        UserItem currentItem = mUserList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mPlace.setText(String.valueOf(currentItem.getPlace()));
        holder.mUsername.setText("Name: " + currentItem.getUsername());
        holder.mLastSeenTime.setText("Total submissions: " + currentItem.getSubmissionsCount());
        holder.mGroup.setText("Submissions this month: " + currentItem.getSubmissionsThisMonth());
        holder.mValue.setText("Age: " + currentItem.getValue());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
