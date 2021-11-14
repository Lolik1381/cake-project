package com.example.heroku.service.impl;

import com.example.heroku.entity.FileEntity;
import com.example.heroku.entity.ProductEntity;
import com.example.heroku.entity.ProductTypeEntity;
import com.example.heroku.mapper.ProductMapper;
import com.example.heroku.model.Product;
import com.example.heroku.model.ResponsePage;
import com.example.heroku.repository.FileRepository;
import com.example.heroku.repository.ProductTypeRepository;
import com.example.heroku.repository.core.CoreProductRepository;
import com.example.heroku.service.ProductService;
import com.example.heroku.util.PageableUtils;
import com.example.heroku.util.sorting.SortFieldMatcherFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final CoreProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;
    private final FileRepository fileRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponsePage<Product> getAllProducts(String searchText, List<String> productTypes, Pageable pageable) {
        pageable = PageableUtils.applySorting(pageable, SortFieldMatcherFactory.getSortFieldMatcher(Product.class));

        Page<ProductEntity> productEntityPage = productRepository.findAll(searchText, productTypes, pageable);
        List<Product> products = productEntityPage.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

        return PageableUtils.getResponsePage(productEntityPage, products);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(String id) {
        return productMapper.toDto(productRepository.findByIdOrElseNull(id));
    }

    @Override
    @Transactional
    public Product create(Product product) throws IllegalArgumentException {
        ProductTypeEntity productTypeEntity = productTypeRepository.findById(product.getProductType().getId()).orElse(null);
        FileEntity file = fileRepository.findByIdOrElseNull(product.getFile().getId());

        if (Objects.isNull(productTypeEntity) || Objects.isNull(file)) {
            throw new IllegalArgumentException("Not found productType or file!");
        }

        ProductEntity productEntity = productMapper.newEntity(product, UUID.randomUUID().toString(), productTypeEntity, file);
        return productMapper.toDto(productRepository.save(productEntity));
    }
}