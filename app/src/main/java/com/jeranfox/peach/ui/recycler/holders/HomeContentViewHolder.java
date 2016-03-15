package com.jeranfox.peach.ui.recycler.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.ui.utility.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeContentViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.explore_item_profile_image)
    ImageView profileImage;

    @Bind(R.id.explore_item_display_name)
    TextView displayName;

    @Bind(R.id.explore_item_last_post)
    TextView lastPost;

    @Bind(R.id.explore_item_time_since_last_post)
    TextView timeSinceLastPost;

    public HomeContentViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void onBindViewHolder(ExploreItem exploreItem) {
        displayName.setText(exploreItem.getDisplayName());
        lastPost.setText(exploreItem.getLastPost());
        timeSinceLastPost.setText(formatLastPostTime(exploreItem.getLastPostTime()));
        itemView.setBackgroundResource(R.drawable.item_bg);
        Picasso.with(itemView.getContext()).load(exploreItem.getAvatarSrc()).transform(new CircleTransformation()).into(profileImage);
    }

    public void onBindViewHolderTop(ExploreItem exploreItem) {
        onBindViewHolder(exploreItem);
        itemView.setBackgroundResource(R.drawable.top_item_bg);
    }

    public void onBindViewHolderBottom(ExploreItem exploreItem) {
        onBindViewHolder(exploreItem);
        itemView.setBackgroundResource(R.drawable.bottom_item_bg);
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
}
