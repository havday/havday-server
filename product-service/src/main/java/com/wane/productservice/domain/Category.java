package com.wane.productservice.domain;

import lombok.Getter;

@Getter
public class Category {

	private Long id;
	private String name;

	private Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
