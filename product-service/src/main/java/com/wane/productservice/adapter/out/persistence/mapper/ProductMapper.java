package com.wane.productservice.adapter.out.persistence.mapper;

import com.wane.productservice.adapter.out.persistence.jpa.category.CategoryEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductDetailImageEntity;
import com.wane.productservice.adapter.out.persistence.jpa.product.ProductEntity;
import com.wane.productservice.domain.Category;
import com.wane.productservice.domain.Product;
import com.wane.productservice.domain.ProductDetailImage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class ProductMapper {

    public Product toDomainEntity(ProductEntity productEntity) {
        return Product.of(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getMaterialDescription(),
                productEntity.getSizeDescription(),
                productEntity.getQuantity(),
                productEntity.getMainImageUrl(),
//                TODO: need to fix
//                productEntity.getCategories().stream().map(this::toDomainEntity).collect(Collectors.toList()),
                null,
                productEntity.getProductDetailImages().stream().map(this::toDomainEntity).collect(Collectors.toList())
        );
    }

    public Product toDomainEntityForMain(ProductEntity productEntity) {
        return Product.forMain(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getMainImageUrl()
        );
    }

    public Product toDomainEntityForDetail(ProductEntity productEntity) {
        return Product.forDetail(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getMaterialDescription(),
                productEntity.getSizeDescription(),
                productEntity.getMainImageUrl(),
                productEntity.getProductDetailImages().stream().map(this::toDomainEntity).toList()
        );
    }

    public ProductDetailImage toDomainEntity(ProductDetailImageEntity productDetailImageEntity) {
        return new ProductDetailImage(
                productDetailImageEntity.getId(),
                productDetailImageEntity.getImageUrl()
        );
    }

    public ProductEntity toJpaEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .materialDescription(product.getMaterialDescription())
                .sizeDescription(product.getSizeDescription())
                .quantity(product.getQuantity())
                .mainImageUrl(product.getMainImageUrl())
//                TODO: 카테고리 이름 변경 및 카테고리 다대다 라서 어떻게 이름 바꿀지 생각
//                .categories()
                .productDetailImages(product.getProductDetailImages().stream().map(this::toJpaEntity).toList())
                .build();
    }

    private CategoryEntity toJpaEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }

    public ProductDetailImageEntity toJpaEntity(ProductDetailImage productDetailImage) {
        return ProductDetailImageEntity.builder()
                .id(productDetailImage.getId())
                .imageUrl(productDetailImage.getImageUrl())
                .build();
    }

}
