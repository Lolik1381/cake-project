package com.example.heroku.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData {

    private String id;
    private String name;
    private String lastname;
    private String patronymic;
    private String email;
    private String phone;
    private City city;
    private DeliveryType deliveryType;
    private String street;
    private String house;
    private String entrance;
    private String floor;
    private String apartmentOffice;
    private String intercom;
    private String comment;
    private DeliveryInterval deliveryInterval;
    private LocalDate deliveryDate;
    private List<ProductDetails> productDetails;
}