package com.example.heroku.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "company")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigserial", updatable = false)
    private Long id;

    @Column(name = "prm_id", nullable = false)
    private Long prmId;

    @Column(name = "name")
    private String brandingName;

    @Column(name = "egrul_name")
    private String egrulName;

    @Column(name = "egrul_short_name")
    private String egrulShortName;

    @Column(name = "inn")
    private String inn;

    @Column(name = "dzo_percent")
    private BigDecimal dzoPercent;

    @Builder.Default
    @Column(name = "can_sell_product", nullable = false)
    private Boolean canSellProduct = true;
}