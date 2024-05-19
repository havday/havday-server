package com.wane.productservice.adapter.in.web.dto.response;

import java.util.List;

public record FindMainProductsResponse(
		boolean hasNext,
		List<FindMainProduct> products
) {
}
