package com.example.heroku.repository.core;

import com.example.heroku.entity.ProductEntity;
import com.example.heroku.repository.ProductRepository;
import com.example.heroku.repository.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoreProductRepository {

    private final ProductRepository productRepository;

    public Page<ProductEntity> findAll(String searchText, Pageable pageable) {
        Specification<ProductEntity> specification = combineSpecifications(
                likeNameCondition(searchText)
        );

        return productRepository.findAll(specification, pageable);
    }

    public ProductEntity findByIdOrElseNull(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @SafeVarargs
    @SuppressWarnings("ConstantConditions")
    private static Specification<ProductEntity> combineSpecifications(Specification<ProductEntity> ... specifications) {
        if (ArrayUtils.isEmpty(specifications)) {
            return null;
        }

        return Stream.of(specifications).filter(Objects::nonNull).reduce(Specification::and).orElse(null);
    }

    public Specification<ProductEntity> likeNameCondition(String searchText) {
        return StringUtils.isNotEmpty(searchText)
                ? (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), RepositoryUtils.getLikeTextValue(searchText))
                : null;
    }
}