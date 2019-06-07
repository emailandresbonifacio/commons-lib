package com.lwg.commons.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Interface to create and bind {@link RecyclerView.ViewHolder}
 */
public interface DelegateAdapter<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> {

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, T item);
}
