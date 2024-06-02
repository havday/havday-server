package com.wane.orderservice.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.orderservice.adapter.in.web.dto.request.CreateOrderRequest;
import com.wane.orderservice.adapter.in.web.dto.request.ProductItemRequest;
import com.wane.orderservice.adapter.in.web.external.CreateOrderController;
import com.wane.orderservice.application.port.in.CreateOrderCommand;
import com.wane.orderservice.application.port.in.CreateOrderUseCase;
import com.wane.orderservice.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreateOrderController.class)
@AutoConfigureRestDocs
class CreateOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;

    @DisplayName("controller: 주문 생성을 성공한다.")
    @Test
    void createOrder() throws Exception {
        //given
        ProductItemRequest productItem1 = new ProductItemRequest(1L, 3);
        ProductItemRequest productItem2 = new ProductItemRequest(3L, 2);
        ProductItemRequest productItem3 = new ProductItemRequest(4L, 100);

        CreateOrderRequest request = new CreateOrderRequest( 1L, 10000, true, 1000, "card", List.of(productItem1, productItem2, productItem3));
        String id = "202405270000001";

        given(createOrderUseCase.createOrder(any(CreateOrderCommand.class)))
                .willReturn(orderWithContainsIdAndCreatedAt(id));

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/orders")
                        .header("memberId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print());
        //then
        resultActions.andExpect(status().isCreated())
                .andDo(document("create-order",
                        requestFields(
                                fieldWithPath("addressId").description("주소 ID"),
                                fieldWithPath("totalPrice").description("총 가격"),
                                fieldWithPath("isDeliveryFeeExists").description("배송비 포함 여부"),
                                fieldWithPath("usedPoint").description("사용한 포인트"),
                                fieldWithPath("paymentType").description("결제 타입"),
                                fieldWithPath("productItems[].productId").description("상품 ID"),
                                fieldWithPath("productItems[].quantity").description("수량")
                        ),
                        responseFields(
                                fieldWithPath("orderId").description("주문 ID"),
                                fieldWithPath("createdDate").description("주문 생성일자")
                        )
                ));

    }

    private static Order orderWithContainsIdAndCreatedAt(String id) {
        return Order.of(id, null, null, 0, false, 0, null, LocalDateTime.of(2024, 5, 27, 12, 59, 59), null, List.of());
    }
}