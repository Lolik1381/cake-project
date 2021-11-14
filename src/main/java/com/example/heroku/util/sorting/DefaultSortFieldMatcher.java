package com.example.heroku.util.sorting;

import org.springframework.lang.NonNull;

import java.util.Set;

public final class DefaultSortFieldMatcher implements SortFieldMatcher {

    private final Set<String> availableSortFields;

    public DefaultSortFieldMatcher(@NonNull Set<String> availableSortFields) {
        this.availableSortFields = availableSortFields;
    }

    @Override
    public String getSortFieldName(String dtoFieldName) {
        if (availableSortFields.contains(dtoFieldName)) {
            return dtoFieldName;
        } else {
            throw new IllegalArgumentException("Unsupported sort field: " + dtoFieldName);
        }
    }
}