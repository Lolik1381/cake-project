package com.example.heroku.util.sorting;

import com.example.heroku.model.Product;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class SortFieldMatcherFactory {

    private static final Map<Class<?>, SortFieldMatcher> SORT_FIELD_MAP = Collections.singletonMap(
            Product.class, new DefaultSortFieldMatcher(Collections.singleton("cost"))
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