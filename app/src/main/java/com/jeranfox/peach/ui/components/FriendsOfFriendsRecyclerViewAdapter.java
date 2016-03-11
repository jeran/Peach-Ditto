package com.jeranfox.peach.ui.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.Friend;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendsOfFriendsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_COUNT = 1;
    private List<Friend> friends = new ArrayList<>();

    public FriendsOfFriendsRecyclerViewAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewType.HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friends_of_friends_header, parent, false));
        } else {
            return new ContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friends_of_friends_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            Friend friend = friends.get(position - HEADER_COUNT);
            contentViewHolder.title.setText(friend.getTitle());
            contentViewHolder.lastPost.setText(friend.getLastPost());
            contentViewHolder.timeSinceLastPost.setText(friend.getTimeSinceLastPost());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewType.HEADER;
        } else {
            return ViewType.CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return friends.size() + HEADER_COUNT;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.fof_item_profile_image)
        ImageView profileImage;

        @Bind(R.id.fof_item_title)
        TextView title;

        @Bind(R.id.fof_item_last_post)
        TextView lastPost;

        @Bind(R.id.fof_item_time_since_last_post)
        TextView timeSinceLastPost;

        public ContentViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    static class ViewType {
        static final int HEADER = 0;
        static final int CONTENT = 1;
    }
}
