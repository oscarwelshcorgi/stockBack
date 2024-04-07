package com.pr.member.dto;

import com.pr.member.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {
    private String name;
    private String email;
    private String picture;

    private String nickName;

    public SessionMember(Member member){
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.nickName = member.getNickName();
    }
}