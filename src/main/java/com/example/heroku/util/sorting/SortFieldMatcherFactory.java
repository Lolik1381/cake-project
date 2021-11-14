package com.example.heroku.util.sorting;

import com.example.heroku.model.Product;

import java.util.Map;
import java.util.Set;

public final class SortFieldMatcherFactory {

    private static final Map<Class<?>, SortFieldMatcher> SORT_FIELD_MAP = Map.of(
            Product.class, new DefaultSortFieldMatcher(Set.of("cost"))
    );

    public static SortFieldMatcher getSortFieldMatcher(Class<?> dtoClass) {
        SortFieldMatcher matcher = SORT_FIELD_MAP.get(dtoClass);
        if (matcher != null) {
            return matcher;
        } else {
            throw new IllegalArgumentException("Unsupported DTO class: " + dtoClass);
        }
    }
}