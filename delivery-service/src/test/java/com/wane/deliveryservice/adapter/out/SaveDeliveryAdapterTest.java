package com.wane.deliveryservice.adapter.out;

import com.wane.deliveryservice.adapter.out.persistence.AddressJpaEntity;
import com.wane.deliveryservice.adapter.out.persistence.DeliveryJpaEntity;
import com.wane.deliveryservice.adapter.out.persistence.DeliveryJpaEntityRepository;
import com.wane.deliveryservice.domain.Delivery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class SaveDeliveryAdapterTest {

    @MockBean
    private DeliveryMapper deliveryMapper;
    @Autowired
    private DeliveryJpaEntityRepository deliveryRepository;

    @Test
    void saveDelivery() {
        // given
        SaveDeliveryAdapter saveDeliveryAdapter = new SaveDeliveryAdapter(deliveryMapper, deliveryRepository);
        Delivery delivery = Delivery.create(1L, "20240608000001", "27479", "분포로 113", "101동 101호", "01012341234", "박재완");

        DeliveryJpaEntity deliveryJpaEntity = toDeliveryJpaEntity(delivery);
        given(deliveryMapper.toJpaEntity(delivery)).willReturn(deliveryJpaEntity);

        // when
        saveDeliveryAdapter.saveDelivery(delivery);

        // then
        assertThat(deliveryRepository.findAll()).hasSize(1);
        DeliveryJpaEntity savedDelivery = deliveryRepository.findAll().get(0);

        assertThat(savedDelivery.getId()).isNotNull();
        assertThat(savedDelivery.getMemberId()).isEqualTo(1L);
        assertThat(savedDelivery.getOrderId()).isEqualTo("20240608000001");
        //TODO. 인보이스 넘버는 현재 빈 값 입니다.
//        assertThat(savedDelivery.getInvoiceNumber()).isEqualTo("");
        assertThat(savedDelivery.getAddress()).isEqualTo(deliveryJpaEntity.getAddress());
        assertThat(savedDelivery.getAddress().getZipCode()).isEqualTo("27479");
        assertThat(savedDelivery.getAddress().getRoadName()).isEqualTo("분포로 113");
        assertThat(savedDelivery.getAddress().getDetail()).isEqualTo("101동 101호");
        assertThat(savedDelivery.getAddress().getPhoneNumber()).isEqualTo("01012341234");
        assertThat(savedDelivery.getAddress().getRecipient()).isEqualTo("박재완");

    }

    private DeliveryJpaEntity toDeliveryJpaEntity(Delivery delivery) {
        return DeliveryJpaEntity.builder()
                .id(delivery.getId())
                .memberId(delivery.getMemberId())
                .orderId(delivery.getOrderId())
                .invoiceNumber(delivery.getInvoiceNumber())
                .deliveryStatus(delivery.getDeliveryStatus())
                .address(toAddressJpaEntity(delivery))
                .build();
    }

    private AddressJpaEntity toAddressJpaEntity(Delivery delivery) {
        return AddressJpaEntity.builder()
                .zipCode(delivery.getAddress().getZipCode())
                .roadName(delivery.getAddress().getRoadName())
                .detail(delivery.getAddress().getDetail())
                .phoneNumber(delivery.getAddress().getPhoneNumber())
                .recipient(delivery.getAddress().getRecipient())
                .build();
    }
}