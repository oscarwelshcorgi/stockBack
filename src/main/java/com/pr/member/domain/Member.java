package com.pr.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String provider;
    private String nickName;
    private String picture;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

    public Member update(String nickName, LocalDateTime updateDate) {
        this.nickName = nickName;
        this.updateDate = LocalDateTime.now();;
        return this;
    }


    public String getRoleKey() {
        return this.role.getKey();
    }
}