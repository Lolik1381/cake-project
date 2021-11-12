package com.example.heroku.util;

import com.example.heroku.model.ResponsePage;
import com.example.heroku.model.ResponsePageParameters;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public final class PageableUtils {

    public static Pageable sortById(@NonNull Pageable pageable) {
        return sortBy(pageable, Sort.Direction.ASC, "id");
    }

    public static Pageable sortBy(@NonNull Pageable pageable, @NonNull Sort.Direction direction, @NonNull String field) {
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, field));
    }

    public static <T extends Serializable> ResponsePage<T> getResponsePage(Page<?> page, List<T> content) {
        return new ResponsePage<>(content, ResponsePageParameters.builder()
                .number(page.getNumber())
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build());
    }
}