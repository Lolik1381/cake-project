package com.example.heroku.service.impl;

import com.example.heroku.entity.BucketEntity;
import com.example.heroku.entity.CityEntity;
import com.example.heroku.entity.DeliveryTypeEntity;
import com.example.heroku.entity.UserDataEntity;
import com.example.heroku.mapper.UserDataMapper;
import com.example.heroku.model.UserData;

import com.example.heroku.repository.CityRepository;
import com.example.heroku.repository.DeliveryTypeRepository;
import com.example.heroku.repository.UserDateRepository;
import com.example.heroku.repository.core.CoreProductRepository;
import com.example.heroku.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataServiceImpl implements UserDataService {

    private final UserDateRepository userDateRepository;
    private final CityRepository cityRepository;
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final CoreProductRepository productRepository;

    private final UserDataMapper userDataMapper;

    @Override
    @Transactional
    public UserData create(UserData userData) {
        CityEntity cityEntity = cityRepository.findByShortDescription(userData.getCity().getShortDescription());
        DeliveryTypeEntity deliveryTypeEntity = deliveryTypeRepository.findById(userData.getDeliveryType().getId()).orElse(null);

        UserDataEntity userDataEntity = userDataMapper.newEntity(userData, cityEntity, deliveryTypeEntity, UUID.randomUUID().toString());

        if (CollectionUtils.isNotEmpty(userData.getProductDetails())) {
            List<BucketEntity> bucketEntities = userData.getProductDetails().stream()
                    .map(productDetails -> BucketEntity.builder()
                            .userData(userDataEntity)
                            .product(productRepository.findByIdOrElseNull(productDetails.getId()))
                            .numberProduct(Long.valueOf(productDetails.getNumberProduct()))
                            .build())
                    .collect(Collectors.toList());
            userDataEntity.setBucket(bucketEntities);
        }

        return userDataMapper.toDto(userDateRepository.save(userDataEntity));
    }
}