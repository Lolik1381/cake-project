package com.example.heroku.repository.core;

import com.example.heroku.entity.ProductEntity;
import com.example.heroku.entity.ProductEntity_;
import com.example.heroku.entity.ProductTypeEntity_;
import com.example.heroku.repository.ProductRepository;
import com.example.heroku.repository.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoreProductRepository {

    private final ProductRepository productRepository;

    public Page<ProductEntity> findAll(String searchText, List<String> productTypes, Pageable pageable) {
        Specification<ProductEntity> specification = combineSpecifications(
                likeNameCondition(searchText),
                productTypeCondition(productTypes)
        );

        return productRepository.findAll(specification, pageable);
    }

    public ProductEntity findByIdOrElseNull(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
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
                ? (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(ProductEntity_.name)), RepositoryUtils.getLikeTextValue(searchText))
                : null;
    }

    public Specification<ProductEntity> productTypeCondition(List<String> productTypes) {
        return CollectionUtils.isNotEmpty(productTypes)
                ? (root, query, criteriaBuilder) -> root.join(ProductEntity_.productType).get(ProductTypeEntity_.id).in(productTypes)
                : null;
    }
}