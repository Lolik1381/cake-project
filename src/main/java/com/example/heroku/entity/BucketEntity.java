package com.example.heroku.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bucket")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BucketEntity {

    @EmbeddedId
    private BucketId id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_data_id")
    @MapsId("userDataId")
    private UserDataEntity userData;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    @MapsId("productId")
    private ProductEntity product;

    @Column(name = "number_product")
    private Long numberProduct;

    @Builder
    public BucketEntity(@NonNull UserDataEntity userData, @NonNull ProductEntity product, @NonNull Long numberProduct) {
        this.id = new BucketEntity.BucketId(userData.getId(), product.getId());
        this.userData = userData;
        this.product = product;
        this.numberProduct = numberProduct;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BucketId implements Serializable {

        @Column(name = "user_data_id", nullable = false, updatable = false)
        private String userDataId;

        @Column(name = "product_id", nullable = false, updatable = false)
        private String productId;
    }
}