package com.lwg.commons.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Base class that simplifies the DelegateAdapter pattern implementation. Extends GenericBaseRecyclerViewAdapter
 * for RecyclerViewTypes.
 * For the accessibility feature, please use com.disney.wdpro.support.recyclerview.AccessibilityDelegateDecorator.
 *
 * @deprecated Use {@link GenericBaseRecyclerViewAdapter} instead.
 * It is planned to be deleted starting on the app version 4.16.
 */
public class BaseRecyclerViewAdapter extends GenericBaseRecyclerViewAdapter<RecyclerViewType> {
    public SparseArrayCompat<AccessibilityDelegateAdapter> accessibilityDelegateAdapters;

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        //We do this check to avoid crashing apps due to the missing init in projects that already use this class.
        if (accessibilityDelegateAdapters != null) {
            AccessibilityDelegateAdapter accessibilityDelegateAdapter = accessibilityDelegateAdapters.get(getItemViewType(position));
            if (accessibilityDelegateAdapter != null) {
                accessibilityDelegateAdapter.onBindViewHolderAccessibility(holder, this.items.get(position));
            }
        }
    }
}
