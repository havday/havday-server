package com.wane.productservice.application.port.out;

import com.wane.productservice.domain.Product;

import java.util.List;

public interface SaveProductsPort {
    void saveProducts(List<Product> products);
}
