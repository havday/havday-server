package com.wane.orderservice.adapter.out.persistence;

import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntityRepository;
import com.wane.orderservice.domain.Order;
import com.wane.orderservice.domain.PaymentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CreateOrderAdapterTest {

    @Autowired
    private CreateOrderAdapter createOrderAdapter;

    @Autowired
    private OrderJpaEntityRepository orderRepository;

    @DisplayName("주문을 생성할때 새로운 id를 생성한다.")
    @Test
    void createOrderWithNewId() {
        //given
        Order order = Order.createOrder(1L, 1L, 10000, true, 10000, PaymentType.NO_BANKBOOK, List.of());

        //when
        Order createdOrder = createOrderAdapter.createOrder(order);

        //then
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String newId = String.format("%s%07d", currentDate, 1);

        assertThat(createdOrder).isNotNull();
        assertThat(createdOrder.getId()).isEqualTo(newId);
    }

    @DisplayName("기존 id 가 있으면 +1 해서 새로운 id를 생성한다.")
    @Test
    void createOrderWithCurrentId() {
        //given
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        saveBeforeOrder(currentDate);

        Order order = Order.createOrder(1L, 1L, 10000, true, 10000, PaymentType.NO_BANKBOOK, List.of());

        //when
        Order createdOrder = createOrderAdapter.createOrder(order);

        //then
        String newId = String.format("%s%07d", currentDate, 2);

        assertThat(createdOrder).isNotNull();
        assertThat(createdOrder.getId()).isEqualTo(newId);
    }

    private void saveBeforeOrder(String currentDate) {
        String beforeId = String.format("%s%07d", currentDate, 1);

        orderRepository.save(
                OrderJpaEntity.builder()
                        .id(beforeId)
                        .memberId(1L)
                        .addressId(1L)
                        .build()
        );
    }

}