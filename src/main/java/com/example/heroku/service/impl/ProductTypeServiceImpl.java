package com.example.heroku.service.impl;

import com.example.heroku.entity.ProductTypeEntity;
import com.example.heroku.mapper.ProductTypeMapper;
import com.example.heroku.model.ProductType;
import com.example.heroku.repository.ProductTypeRepository;
import com.example.heroku.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductType> findAll() {
        return productTypeRepository.findAll().stream().map(productTypeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductType findByName(String name) {
        return productTypeMapper.toDto(productTypeRepository.findByName(name));
    }

    @Override
    @Transactional
    public ProductType create(ProductType productType) {
        ProductTypeEntity productTypeEntity = productTypeRepository.save(productTypeMapper.newEntity(productType));
        return productTypeMapper.toDto(productTypeEntity);
    }
}