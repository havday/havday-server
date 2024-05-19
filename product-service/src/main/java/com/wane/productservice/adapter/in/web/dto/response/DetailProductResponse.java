package com.wane.productservice.adapter.in.web.dto.response;

import java.util.List;

public record DetailProductResponse(
		Long id,
		String name,
		int price,
		String materialDescription,
		String sizeDescription,
		String mainImageUrl,
		List<String> detailImageUrls
) {


}
