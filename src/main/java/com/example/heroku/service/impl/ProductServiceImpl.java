package com.example.heroku.service.impl;

import com.example.heroku.entity.ProductEntity;
import com.example.heroku.mapper.ProductMapper;
import com.example.heroku.model.Product;
import com.example.heroku.model.ResponsePage;
import com.example.heroku.repository.ProductRepository;
import com.example.heroku.repository.core.CoreProductRepository;
import com.example.heroku.service.ProductService;
import com.example.heroku.util.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {

    private final CoreProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponsePage<Product> getAllProducts(String searchText, Pageable pageable) {
        pageable = PageableUtils.sortById(pageable);

        Page<ProductEntity> productEntityPage = productRepository.findAll(searchText, pageable);
        List<Product> products = productEntityPage.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

        return PageableUtils.getResponsePage(productEntityPage, products);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(String id) {
        return productMapper.toDto(productRepository.findByIdOrElseThrow(id));
    }
}