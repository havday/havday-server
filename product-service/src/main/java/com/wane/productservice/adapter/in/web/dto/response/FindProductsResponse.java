package com.wane.productservice.adapter.in.web.dto.response;

import java.util.List;

public record FindProductsResponse(
		boolean hasNext,
		List<FindProduct> products
) {
}
