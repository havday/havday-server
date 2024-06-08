package com.wane.deliveryservice.adapter.out.persistence;

import com.wane.deliveryservice.domain.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "delivery")
public class DeliveryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String orderId;

    private String invoiceNumber;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "address_id")
    private AddressJpaEntity address;

    @Builder
    public DeliveryJpaEntity(Long id, Long memberId, String orderId, String invoiceNumber, DeliveryStatus deliveryStatus, AddressJpaEntity address) {
        this.id = id;
        this.memberId = memberId;
        this.orderId = orderId;
        this.invoiceNumber = invoiceNumber;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }
}
