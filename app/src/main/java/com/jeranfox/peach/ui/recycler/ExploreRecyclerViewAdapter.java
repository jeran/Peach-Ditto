package com.jeranfox.peach.ui.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeranfox.peach.R;
import com.jeranfox.peach.entities.ExploreItem;
import com.jeranfox.peach.ui.recycler.holders.HomeContentViewHolder;

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
            return new HomeContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.explore_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT) {
            HomeContentViewHolder contentViewHolder = (HomeContentViewHolder) holder;
            ExploreItem exploreItem = exploreItems[position - HEADER_COUNT];
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

    static class ViewType {
        static final int HEADER = 0;
        static final int CONTENT = 1;
        static final int FOOTER = 2;
    }
}
