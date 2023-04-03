package com.lwg.commons.adapter;


import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Interface to create and bind {@link RecyclerView.ViewHolder}
 */
public interface DelegateAdapter<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> {

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, T item);
}
