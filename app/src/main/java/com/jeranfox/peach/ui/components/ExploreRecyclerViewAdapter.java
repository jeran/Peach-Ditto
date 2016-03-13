package com.jeranfox.peach.ui.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.ui.utility.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExploreRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_COUNT = 1;
    private static final int FOOTER_COUNT = 1;
    private ExploreItem[] exploreItems = new ExploreItem[0];

    public ExploreRecyclerViewAdapter(ExploreItem[] exploreItems) {
        this.exploreItems = exploreItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewType.HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_header, parent, false));
        } else if (viewType == ViewType.FOOTER) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_footer, parent, false));
        } else {
            return new ContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            ExploreItem exploreItem = exploreItems[position - HEADER_COUNT];
            contentViewHolder.displayName.setText(exploreItem.getDisplayName());
            contentViewHolder.lastPost.setText(exploreItem.getLastPost());
            contentViewHolder.timeSinceLastPost.setText(formatLastPostTime(exploreItem.getLastPostTime()));
            int backgroundResourceId = R.drawable.item_bg;
            if (position == HEADER_COUNT) {
                backgroundResourceId = R.drawable.top_item_bg;
            } else if (position == getItemCount() - FOOTER_COUNT - 1) {
                backgroundResourceId = R.drawable.bottom_item_bg;
            }
            contentViewHolder.itemView.setBackgroundResource(backgroundResourceId);
            Picasso.with(contentViewHolder.itemView.getContext()).load(exploreItem.getAvatarSrc()).transform(new CircleTransformation()).into(contentViewHolder.profileImage);
        }
    }

    private static String formatLastPostTime(int lastPostTime) {
        if (lastPostTime < 0) {
            return "";
        } else {
            long timeDeltaSeconds = System.currentTimeMillis() / 1000 - lastPostTime;
            if (timeDeltaSeconds < 60) {
                return TimeUnit.SECONDS.toSeconds(timeDeltaSeconds) + "s";
            } else if (timeDeltaSeconds < 60 * 60) {
                return TimeUnit.SECONDS.toMinutes(timeDeltaSeconds) + "m";
            } else if (timeDeltaSeconds < 60 * 60 * 24) {
                return TimeUnit.SECONDS.toHours(timeDeltaSeconds) + "h";
            } else {
                return TimeUnit.SECONDS.toDays(timeDeltaSeconds) + "d";
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewType.HEADER;
        } else if (position == exploreItems.length + HEADER_COUNT) {
            return ViewType.FOOTER;
        } else {
            return ViewType.CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return exploreItems.length + HEADER_COUNT + FOOTER_COUNT;
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.explore_item_profile_image)
        ImageView profileImage;

        @Bind(R.id.explore_item_display_name)
        TextView displayName;

        @Bind(R.id.explore_item_last_post)
        TextView lastPost;

        @Bind(R.id.explore_item_time_since_last_post)
        TextView timeSinceLastPost;

        public ContentViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    static class ViewType {
        static final int HEADER = 0;
        static final int CONTENT = 1;
        static final int FOOTER = 2;
    }
}
