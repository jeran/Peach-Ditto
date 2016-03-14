package com.jeranfox.peach.ui.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeranfox.peach.R;

public class FeedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_COUNT = 2;
    private static final int FOOTER_COUNT = 1;
    private Object[] feedItems = new Object[10];

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
            return new ContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            int backgroundResourceId = R.drawable.item_bg;
            if (position == HEADER_COUNT) {
                backgroundResourceId = R.drawable.top_item_bg;
            } else if (position == getItemCount() - FOOTER_COUNT - 1) {
                backgroundResourceId = R.drawable.bottom_item_bg;
            }
            contentViewHolder.itemView.setBackgroundResource(backgroundResourceId);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewType.SEARCH;
        } else if (position == 1) {
            return ViewType.HEADER;
        } else if (position == feedItems.length + HEADER_COUNT) {
            return ViewType.FOOTER;
        } else {
            return ViewType.CONTENT;
        }
    }

    @Override
    public int getItemCount() {
        return feedItems.length + HEADER_COUNT + FOOTER_COUNT;
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

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        public ContentViewHolder(View v) {
            super(v);
        }
    }

    static class ViewType {
        static final int SEARCH = 0;
        static final int HEADER = 1;
        static final int CONTENT = 2;
        static final int FOOTER = 3;
    }
}
