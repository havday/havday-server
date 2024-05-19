package com.wane.productservice.common;

import java.util.List;

public record CursorResponse<T>(
		boolean hasNext,
		List<T> data
) {
}
