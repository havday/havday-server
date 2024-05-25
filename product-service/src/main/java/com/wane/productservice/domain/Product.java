package com.wane.productservice.domain;

import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import lombok.Getter;

import java.util.List;

@Getter
public class Product {

	private Long id;
	private String name;
	private int price;
	private String materialDescription;
	private String sizeDescription;
	private int quantity;
	private String mainImageUrl;
	private List<Category> categories;
	private List<ProductDetailImage> productDetailImages;

	private Product(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String mainImageUrl, List<ProductDetailImage> productDetailImages) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.materialDescription = materialDescription;
		this.sizeDescription = sizeDescription;
		this.quantity = quantity;
		this.mainImageUrl = mainImageUrl;
		this.productDetailImages = productDetailImages;
	}

	private Product(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String mainImageUrl, List<Category> categories, List<ProductDetailImage> productDetailImages) {
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

	//TODO : of 대신 다른 이름 생각하기
	public static Product of(Long id, String name, int price, String materialDescription, String sizeDescription, int quantity, String mainImageUrl, List<Category> categories, List<ProductDetailImage> productDetailImages) {
		return new Product(id, name, price, materialDescription, sizeDescription, quantity, mainImageUrl, categories, productDetailImages);
	}

	public static Product forMain(Long id, String name, int price, String mainImageUrl) {
		return new Product(id, name, price, "", "", 0, mainImageUrl, List.of());
	}

	public static Product forDetail(Long id, String name, int price, String materialDescription, String sizeDescription, String mainImageUrl, List<ProductDetailImage> productDetailImages) {
		return new Product(id, name, price, materialDescription, sizeDescription, 0, mainImageUrl, productDetailImages);
	}

	public void decreaseQuantity(Integer quantity) {
		if (this.quantity < quantity) {
			throw new CustomException(ErrorCode.PRODUCT_QUANTITY_NOT_ENOUGH);
		}
		this.quantity -= quantity;
	}
}
