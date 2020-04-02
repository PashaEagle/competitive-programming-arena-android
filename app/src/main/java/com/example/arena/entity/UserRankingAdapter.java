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

    public static class UserRankingViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public UserRankingViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.rankingUserItemImageView);
            mTextView1 = itemView.findViewById(R.id.rankingUserItemText1);
            mTextView2 = itemView.findViewById(R.id.rankingUserItemText2);
        }
    }

    public UserRankingAdapter(ArrayList<UserItem> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public UserRankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_user_item, parent, false);
        UserRankingViewHolder urvh = new UserRankingViewHolder(v);
        return urvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRankingViewHolder holder, int position) {
        UserItem currentItem = mUserList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
