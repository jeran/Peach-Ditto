package com.jeranfox.peach.ui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.entities.FeedData;
import com.jeranfox.peach.ui.recycler.holders.HomeContentViewHolder;

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_COUNT = 2;
    private static final int FOOTER_COUNT = 1;
    private FeedData feedData;

    public FeedRecyclerViewAdapter(FeedData feedData) {
        this.feedData = feedData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewType.SEARCH) {
            return new SearchViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_header, parent, false));
        } else if (viewType == ViewType.HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.feed_header, parent, false));
        } else if (viewType == ViewType.FOOTER) {
            return new FooterViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_footer, parent, false));
        } else {
            return new HomeContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT) {
            HomeContentViewHolder contentViewHolder = (HomeContentViewHolder) holder;
            ExploreItem exploreItem = feedData.getExploreItem(position - HEADER_COUNT);
            contentViewHolder.onBindViewHolder(exploreItem);
            if (position == HEADER_COUNT) {
                contentViewHolder.onBindViewHolderTop(exploreItem);
            } else if (position == getItemCount() - FOOTER_COUNT - 1) {
                contentViewHolder.onBindViewHolderBottom(exploreItem);
            } else {
                contentViewHolder.onBindViewHolder(exploreItem);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewType.SEARCH;
        } else if (position == 1) {
            return ViewType.HEADER;
        } else if (position == feedData.getSize() + HEADER_COUNT) {
            return ViewType.FOOTER;
        } else {
            return ViewType.CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return feedData.getSize() + HEADER_COUNT + FOOTER_COUNT;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        public SearchViewHolder(View itemView) {
            super(itemView);
        }
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

    static class ViewType {
        static final int SEARCH = 0;
        static final int HEADER = 1;
        static final int CONTENT = 2;
        static final int FOOTER = 3;
    }
}
