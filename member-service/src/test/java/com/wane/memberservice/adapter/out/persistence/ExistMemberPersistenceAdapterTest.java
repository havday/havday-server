package com.wane.memberservice.adapter.out.persistence;

import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntity;
import com.wane.memberservice.adapter.out.persistence.jpa.MemberJpaEntityRepository;
import com.wane.memberservice.domain.AuthServiceType;
import com.wane.memberservice.domain.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ExistMemberPersistenceAdapterTest {

    @Autowired
    private ExistMemberPersistenceAdapter adapter;

    @Autowired
    private MemberJpaEntityRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @DisplayName("회원이 존재하는지 id 로 확인한다.")
    @Test
    void existMemberByMemberId() {
        //given
        MemberJpaEntity member = new MemberJpaEntity(1L, "박재완", "wan2daaa@gmail.com", "1234", "phoneNumber", 10000, AuthServiceType.KAKAO, MemberRole.USER, null, "");
        repository.save(member);

        //when
        boolean exists = adapter.existMemberByMemberId(member.getId().toString());

        //then
        assertThat(exists).isTrue();
    }

    @DisplayName("회원이 존재하지 않는지 id 로 확인한다.")
    @Test
    void notExistMemberByMemberId() {
        //given
        String id = "2";

        //when
        boolean exists = repository.existsById(Long.valueOf(id));

        //then
        assertThat(exists).isFalse();
    }
}