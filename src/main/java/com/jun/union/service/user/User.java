package com.jun.union.service.user;

import com.jun.union.define.enums.ERole;
import com.jun.union.dto.common.DefaultEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends DefaultEntity {

    @Id
    // 이메일
    private String email;
    // 비밀 번호.
    @Column(nullable = false)
    private String password;
    // 이름.
    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<ERole> roles = new ArrayList<>();

    @Builder
    public User(String email, String password, List<ERole> roles) {
        this.email = email;
        this.password = password;
        this.roles = Collections.singletonList(ERole.ROLE_MEMBER);
    }

    public void addRole(ERole role) {
        this.roles.add(role);
    }
}
