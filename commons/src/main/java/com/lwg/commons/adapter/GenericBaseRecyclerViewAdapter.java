package com.lwg.commons.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Lists;
import com.lwg.commons.utils.CollectionsUtils;

import java.util.List;
import java.util.Map;

/**
 * Generic {@link com.lwg.commons.adapter}.
 */
public class GenericBaseRecyclerViewAdapter<T extends RecyclerViewType> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int NO_POSITION = -1;
    public final List<T> items = Lists.newArrayList();
    public SparseArrayCompat<DelegateAdapter> delegateAdapters;

    public GenericBaseRecyclerViewAdapter() {
        this.delegateAdapters = new SparseArrayCompat<>();
    }

    public GenericBaseRecyclerViewAdapter(@NonNull Map<Integer, DelegateAdapter> delegateAdapterMap) {
        this.delegateAdapters = new SparseArrayCompat<>(delegateAdapterMap.size());
        for (Map.Entry<Integer, DelegateAdapter> delegateAdapterEntry : delegateAdapterMap.entrySet()) {
            this.delegateAdapters.put(delegateAdapterEntry.getKey(), delegateAdapterEntry.getValue());
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Adds a {@link T} item to {@link #items} if it is not present
     * and notify the {@link RecyclerView.Adapter}
     *
     * @param viewType to be added
     */
    public void addViewTypeOnceAndNotify(T viewType) {
        addViewTypeOnceAndNotify(items.size(), viewType);
    }

    /**
     * Adds a {@link T} item at the {@param position} to {@link #items} if it is not present
     * and notify the {@link RecyclerView.Adapter}
     *
     * @param viewType to be added
     * @param position position in the items list to insert the item
     */
    public void addViewTypeOnceAndNotify(int position, T viewType) {
        int viewTypePosition = items.indexOf(viewType);
        if (viewTypePosition == NO_POSITION) {
            items.add(position, viewType);
            notifyItemInserted(position);
        }
    }

    /**
     * Remove a {@link T} item from {@link #items} if it is present
     * and notify the {@link RecyclerView.Adapter}
     *
     * @param viewType to be removed from {@link #items}
     * @return the position where {@param viewType} was
     */
    public int removeViewTypeAndNotify(T viewType) {
        int viewTypePosition = items.indexOf(viewType);
        if (viewTypePosition != NO_POSITION) {
            items.remove(viewTypePosition);
            notifyItemRemoved(viewTypePosition);
        }
        return viewTypePosition;
    }

    /**
     * Gets the requested view type position in the list.
     *
     * @param viewType View Type to get the position.
     * @return The position of the view type, or -1 if the view type wasn't found.
     */
    public int getViewTypePosition(T viewType) {
        if (!CollectionsUtils.isNullOrEmpty(items)) {
            return items.indexOf(viewType);
        }
        return NO_POSITION;
    }

    /**
     * Notifies that a section has ben changed.
     *
     * @param viewType View Type of the section that has changed.
     */
    public void notifySectionChanged(T viewType) {
        int viewTypePosition = getViewTypePosition(viewType);
        if (viewTypePosition != NO_POSITION) {
            notifyItemChanged(viewTypePosition);
        }
    }

    /**
     * Clear all {@link #items} and notify the {@link RecyclerView.Adapter}
     */
    public void clearItemsAndNotify() {
        if (!this.items.isEmpty()) {
            int size = this.items.size();
            this.items.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    /**
     * Removes all the views from the adapter starting at {@param pos}, exclusive, up to the end of the list.
     *
     * @param pos the position of the view in the items list.
     */
    public void removeItemsAfterAndNotify(int pos) {
        pos++;
        if (pos > NO_POSITION && pos < items.size()) {
            //We need to create a parallel list to avoid a crash due to ConcurrentModificationException
            List<T> itemsToRemove = Lists.newArrayList(items.subList(pos, items.size()));
            items.removeAll(itemsToRemove);
            notifyItemRangeRemoved(pos, itemsToRemove.size());
        }
    }

    /**
     * Removes all the views from the adapter after {@param viewType}, exclusive, up to the end of the list.
     * If there are two or more views of {@param viewType} type on the list, it will remove all the views after the first occurrence of that
     * view type.
     *
     * @param viewType the viewType.
     */
    public void removeItemsAfterAndNotify(T viewType) {
        int viewPos = items.indexOf(viewType);
        removeItemsAfterAndNotify(viewPos);
    }

    /**
     * Clears the items list and add the specified item.
     *
     * @param item item to be added.
     */
    public void setItemAndNotify(T item) {
        this.items.clear();
        this.items.add(item);
        notifyDataSetChanged();
    }

    /**
     * Clears the items list and add the items in the list.
     *
     * @param items
     */
    public void setItemsAndNotify(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
