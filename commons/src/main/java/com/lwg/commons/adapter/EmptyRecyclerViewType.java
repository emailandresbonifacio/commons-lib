package com.lwg.commons.adapter;

/**
 * {@link RecyclerViewType} to avoid creating inner class that just return a constant value on {@link RecyclerViewType#getViewType()}
 */
public class EmptyRecyclerViewType implements RecyclerViewType {
    private final int viewType;

    public EmptyRecyclerViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return this.viewType;
    }
}
