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
@Table(name = "delivery_interval")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryIntervalEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;
}