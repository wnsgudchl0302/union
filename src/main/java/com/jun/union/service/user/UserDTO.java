package com.jun.union.service.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserDTO extends User {

    private String token;
    private String newPassword;

    public UserDTO(String email) {
        super.setEmail(email);
    }

    public UserDTO(String email, String password) {
        super.setEmail(email);
        super.setPassword(password);
    }
}
