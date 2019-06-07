package com.lwg.commons.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionsUtils {
    private CollectionsUtils() {
    }

    /**
     * Puts the key/value if the key and value both are non-null.
     *
     * @param mapBuilder the map builder to add the key/value to
     * @param key        nullable key
     * @param value      nullable value
     *
     * @throws  if mapBuilder is null
     */
    public static <K, V> void addToMapBuilderIfNotNull(ImmutableMap.Builder<K, V> mapBuilder, K key, V value) {
        Preconditions.checkNotNull(mapBuilder, "map builder == null");

        if (key != null && value != null) {
            mapBuilder.put(key, value);
        }
    }

    /**
     * Puts the element if the element is non-null.
     *
     * @param setBuilder the map builder to add the key/value to
     * @param element    nullable element
     *
     * @throws NullPointerException if setBuilder is null
     */
    /**
    public static <E> void addToSetBuilderIfNotNull(ImmutableSet.Builder<E> setBuilder, E element) {
        Preconditions.checkNotNull(setBuilder, "set builder == null");

        if (element != null) {
            setBuilder.add(element);
        }
    }

    /**
     * This method checks if a Collection is empty
     *
     * @param collection Collection to check
     *
     * @return true if Collection is null, otherwise false
     */
    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * This method checks if a Map is empty
     *
     * @param map Map to check
     *
     * @return true if Map is null, otherwise false
     */
    public static boolean isNullOrEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * @param collection Collection to check
     *
     * @return the given {@link Collection}
     *
     * @throws NullPointerException if {@param collection} is null or empty
     */
    public static <T> Collection<T> checkNotNullOrEmpty(Collection<T> collection, String errorMessage) {
        if (isNullOrEmpty(collection)) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return collection;
        }
    }

    /**
     * Compares if two lists are equals.
     * @param list1
     * @param list2
     * @return True if they are equals, false otherwise.
     */

    /**
     * FIXME - This method has some cases in which returns true and it shouldn't:
     * 1. If the lists are both empty but have different types it returns true (should return false)
     * 2. If one of the lists is null and the other one is empty it returns true (should return false)
     */
    public static boolean equals(List<?> list1, List<?> list2) {

        if (CollectionsUtils.isNullOrEmpty(list1) && CollectionsUtils.isNullOrEmpty(list2)) {
            return true;
        }

        if (list1 != null) {
            return list1.equals(list2);
        }

        return list2.equals(list1);
    }
}
