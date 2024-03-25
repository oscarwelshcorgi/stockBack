package com.pr.member.service;

import com.pr.member.domain.Member;
import com.pr.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * 중복 이메일 검증 후 저장
     * @param member 저장할 회원 정보
     * @return 저장된 회원의 이메일
     * @throws IllegalArgumentException 중복된 이메일인 경우
     */
    public String join(Member member) {
        validateDuplicateEmail(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getEmail();
    }

    /**
     * 중복 이메일 검증
     *
     * @param email 검증할 이메일
     * @throws IllegalArgumentException 중복된 이메일인 경우
     */
    private void validateDuplicateEmail(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 이메일로 회원 조회
     *
     * @param email 조회할 회원의 이메일
     * @return 조회된 회원(Optional)
     */
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}