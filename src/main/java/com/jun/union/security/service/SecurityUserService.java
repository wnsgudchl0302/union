package com.jun.union.security.service;

import com.jun.union.security.dto.SecurityUserDTO;
import com.jun.union.service.user.UserEntity;
import com.jun.union.service.user.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return new SecurityUserDTO(
            userEntity.getEmail()
            , encoder.encode(userEntity.getPassword())
            , userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet())
        );
    }
}
