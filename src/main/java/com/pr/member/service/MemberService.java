package com.pr.member.service;

import com.pr.member.domain.Member;
import com.pr.member.dto.OAuthAttributes;
import com.pr.member.dto.SessionUser;
import com.pr.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public MemberService(MemberRepository memberRepository, HttpSession httpSession) {
        this.memberRepository = memberRepository;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Oauth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // Oauth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OauthUserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = save(attributes);

        httpSession.setAttribute("member", new SessionUser(member));

        return buildOAuth2User(member);
    }

    // OAuth2User 객체 생성
    private OAuth2User buildOAuth2User(Member member) {
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey()));
        Map<String, Object> attributes = Map.of(
                "email", member.getEmail(),
                "name", member.getName(),
                "picture", member.getPicture()
        );

        return new DefaultOAuth2User(authorities, attributes, "email");
    }

    // 유저 생성
    private Member save(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                //.map(entity -> entity.update(attributes.getName(), attributes.getPicture(), attributes.getProvider())) // email을 검색해 사용자가 존재하면 update
                .orElse(attributes.toEntity()); // 비어있으면 새로운 데이터 insert

        return memberRepository.save(member); //최종적으로 저장 or 업데이트 한다.
    }

    // 사용자 정보 업데이트 메서드
    public void updateMember(OAuthAttributes attributes) {
        memberRepository.findByEmail(attributes.getEmail())
                .ifPresent(entity -> entity.update(attributes.getNickName(), attributes.getUpdateDate()));
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

}