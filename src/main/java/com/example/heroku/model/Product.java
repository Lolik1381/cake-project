package com.example.heroku.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {

    private String id;
    private String name;
    private Long cost;
    private String description;
    private String composition;
    private String worth;
    private File file;
    private Double weight;
    private ProductType productType;
}