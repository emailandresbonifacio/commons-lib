package com.lwg.commons.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Generic delegate adapter that allows to inflate views inside the recycler view with no dynamic binding
 */
public class GenericDelegateAdapter<T extends RecyclerViewType> implements DelegateAdapter<GenericDelegateAdapter.GenericViewHolder, T> {

    @LayoutRes
    private int layoutId;

    private View.OnClickListener onClickListener;

    public GenericDelegateAdapter(@LayoutRes int layoutId) {
        this(layoutId, null);
    }

    public GenericDelegateAdapter(@LayoutRes int layoutId, View.OnClickListener listener) {
        this.layoutId = layoutId;
        this.onClickListener = listener;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent) {
        return new GenericViewHolder(parent, layoutId, onClickListener);
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, T item) {
        //no-op
    }

    static class GenericViewHolder extends RecyclerView.ViewHolder {

        GenericViewHolder(ViewGroup parent, @LayoutRes int layoutId, View.OnClickListener listener) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
            // This check is to avoid inconsistencies with Accessibility since a view with a clickListener set is handle as a button
            // and reads out "double tap to activate" when no applies.
            if (listener != null) {
                itemView.setOnClickListener(listener);
            }
        }
    }

}
