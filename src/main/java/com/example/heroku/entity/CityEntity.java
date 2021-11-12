package com.example.heroku.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "address")
    private String address;
}