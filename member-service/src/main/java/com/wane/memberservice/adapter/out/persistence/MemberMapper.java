package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.AddressJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.domain.Address;
import com.wane.memberservice.domain.Member;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MemberMapper {

    public Member toDomainEntity(MemberJpaEntity jpaEntity) {
        return Member.create(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getEmail(),
                jpaEntity.getPassword(),
                jpaEntity.getPhoneNumber(),
                jpaEntity.getPoint(),
                jpaEntity.getAuthServiceType(),
                jpaEntity.getMemberRole(),
                jpaEntity.getAuthId(),
                jpaEntity.getAddresses()== null ? null : jpaEntity.getAddresses().stream().map(this::toDomainEntity).collect(Collectors.toList())
        );
    }

    public Address toDomainEntity(AddressJpaEntity jpaEntity) {
        return Address.create(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getZipCode(),
                jpaEntity.getRoadName(),
                jpaEntity.getDetail(),
                jpaEntity.getPhoneNumber(),
                jpaEntity.getRecipient(),
                jpaEntity.isBaseAddress()
        );
    }

    public MemberJpaEntity toJpaEntity(Member member) {
        return new MemberJpaEntity(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword(),
                member.getPhoneNumber(),
                member.getPoint(),
                member.getAuthServiceType(),
                member.getRole(),
                member.getAddresses().stream().map(this::toJpaEntity).toList(),
                member.getAuthId()
        );
    }

    public AddressJpaEntity toJpaEntity(Address address) {
        return new AddressJpaEntity(
                address.getName(),
                address.getZipCode(),
                address.getRoadName(),
                address.getDetail(),
                address.getPhoneNumber(),
                address.getRecipient(),
                address.isBaseAddress()
        );
    }
}
