package com.jun.union.service.user;

import com.jun.union.define.enums.EResultCode;
import com.jun.union.dto.ResponseDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUserFineByEmail(UserDTO dto) {
        if (dto.getEmail() == null) {
            return null;
        }
        return userRepository.findByEmail(dto.getEmail()).orElse(null);
    }

}
