package com.wane.productservice.adapter.out.persistence.jpa.product;

import com.wane.productservice.adapter.out.persistence.jpa.productcategory.ProductCategoryEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @Lob
    private String materialDescription;

    @Lob
    private String sizeDescription;

    private int quantity;

    private String mainImageUrl;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ProductCategoryEntity> categories = new LinkedHashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductDetailImageEntity> productDetailImages = new ArrayList<>();


    public void setCategories(List<ProductCategoryEntity> list) {
        for (ProductCategoryEntity productCategory : list) {
            if (!this.categories.contains(productCategory)) {
                this.categories.add(productCategory);
                productCategory.setParent(this);
            }
        }
    }

    @Builder
    public ProductEntity(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String mainImageUrl, Set<ProductCategoryEntity> categories, List<ProductDetailImageEntity> productDetailImages) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.materialDescription = materialDescription;
        this.sizeDescription = sizeDescription;
        this.quantity = quantity;
        this.mainImageUrl = mainImageUrl;
        this.categories = categories;
        this.productDetailImages = productDetailImages;
    }
}
