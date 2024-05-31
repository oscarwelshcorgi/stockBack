package com.pr.member.repository;

import com.pr.member.domain.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberInfo, Long> {
    Optional<MemberInfo> findByEmail(String email); // 이미 생성된 사용자인지 체크(email 기준으로 체크)
}
