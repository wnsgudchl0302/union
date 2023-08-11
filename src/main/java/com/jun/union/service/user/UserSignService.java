package com.jun.union.service.user;

import com.jun.union.define.consts.ProjectConst;
import com.jun.union.define.enums.EResultCode;
import com.jun.union.define.enums.ERole;
import com.jun.union.dto.ResponseDataDTO;
import com.jun.union.security.jwt.JwtTokenProvider;
import com.jun.union.security.redis.RedisUtil;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserSignService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;

    /**
     * 회원가입
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDataDTO<UserDTO> register(UserDTO dto) {
        // 유저 존재 여부 확인
        if (userService.getUserFineByEmail(dto) != null) {
            return new ResponseDataDTO<>(EResultCode.REGISTER_FAIL_ALREADY_EMAIL);
        }
        // 이메일 정규식 확인
        if (validationEmail(dto.getEmail())) {
            return new ResponseDataDTO<>(EResultCode.REGISTER_FAIL_FORMAT_EMAIL);
        }
        // 비밀번호 정규식 확인
        if (validationPassword(dto.getPassword())) {
            return new ResponseDataDTO<>(EResultCode.REGISTER_FAIL_FORMAT_PASSWORD);
        }
        // 이름 확인
        if (dto.getName() == null) {
            return new ResponseDataDTO<>(EResultCode.REGISTER_FAIL_NO_NAME);
        }
        UserEntity userEntity = saveUser(dto);
        return new ResponseDataDTO<>(EResultCode.SUCCESS, new UserDTO(userEntity.getEmail()));
    }

    @Transactional(rollbackFor = Exception.class)
    public UserEntity saveUser(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.addRole(ERole.ROLE_MEMBER);
        entity.setName(dto.getName());
        return userRepository.save(entity);
    }

    public boolean validationEmail(String email) {
        return !Pattern.compile(ProjectConst.EMAIL_REGEX).matcher(email).matches();
    }

    public boolean validationPassword(String password) {
        if (password == null) {
            return true;
        }
        return !Pattern.compile(ProjectConst.PASSWORD_REGEX).matcher(password).matches();
    }

    /**
     * 로그인
     *
     * @param dto
     * @return
     */
    public ResponseDataDTO<UserDTO> login(UserDTO dto) {
        UserEntity userEntity = userRepository.findByEmail(dto.getEmail()).orElse(null);

        if (userEntity == null) {
            return new ResponseDataDTO<>(EResultCode.LOGIN_FAIL_NOT_FIND_USER, new UserDTO(dto.getEmail()));
        }

        if (isMatchPassword(dto.getPassword(), userEntity.getPassword())) {
            return new ResponseDataDTO<>(EResultCode.LOGIN_FAIL_WRONG_PASSWORD, new UserDTO(dto.getEmail()));
        }

        UserDTO userDTO = jwtTokenProvider.generateToken(dto.getEmail());
        userDTO.setEmail(dto.getEmail());
        if (redisUtil.hasKeyBlackList(dto.getEmail())) {
            redisUtil.deleteBlackList(dto.getEmail());
        }
        return new ResponseDataDTO<>(EResultCode.SUCCESS, userDTO);
    }

    /**
     * 로그아웃
     *
     * @param dto
     * @return
     */
    public ResponseDataDTO<UserDTO> logout(UserDTO dto) {
        redisUtil.setBlackList(dto.getEmail(), ProjectConst.LOGOUT, 50000L);
        return new ResponseDataDTO<>(EResultCode.SUCCESS, dto);
    }

    /**
     * 비밀번호 변경
     *
     * @param dto
     * @return
     */
    public ResponseDataDTO<UserDTO> changePassword(UserDTO dto) {
        UserEntity userEntity = userService.getUserFineByEmail(dto);

        if (userEntity == null) {
            return new ResponseDataDTO<>(EResultCode.CHANGE_PASSWORD_FAIL_NOT_FIND_USER);
        }

        if (isMatchPassword(dto.getPassword(), userEntity.getPassword())) {
            return new ResponseDataDTO<>(EResultCode.CHANGE_PASSWORD_FAIL_WRONG_PASSWORD);
        }

        if (validationPassword(dto.getNewPassword())) {
            return new ResponseDataDTO<>(EResultCode.CHANGE_PASSWORD_FAIL_FORMAT_PASSWORD);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userEntity.setPassword(encoder.encode(dto.getNewPassword()));
        userRepository.save(userEntity);
        redisUtil.setBlackList(dto.getEmail(), ProjectConst.LOGOUT, 50000L);
        return new ResponseDataDTO<>(EResultCode.SUCCESS, new UserDTO(dto.getEmail()));
    }

    private boolean isMatchPassword(String inputPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return !encoder.matches(inputPassword, storedPassword);
    }

    /**
     * 임시 비밀번호 발급
     *
     * @param dto
     * @return
     */
    public ResponseDataDTO<UserDTO> issueTemporaryPassword(UserDTO dto) {
        UserEntity userEntity = userService.getUserFineByEmail(dto);

        if (userEntity == null) {
            return new ResponseDataDTO<>(EResultCode.ISSUE_TEMPORARY_PASSWORD_FAIL_NOT_FIND_USER, dto);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String temporaryPassword = generateTemporaryPassword();
        userEntity.setPassword(encoder.encode(temporaryPassword));
        userRepository.save(userEntity);
        redisUtil.setBlackList(dto.getEmail(), ProjectConst.LOGOUT, 50000L);

        return new ResponseDataDTO<>(EResultCode.SUCCESS, new UserDTO(dto.getEmail(), temporaryPassword));
    }

    private String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int choice = random.nextInt(3);
            if (choice == 0) {
                sb.append((char) ('A' + random.nextInt(26)));
            } else if (choice == 1) {
                sb.append((char) ('a' + random.nextInt(26)));
            } else {
                sb.append((char) ('0' + random.nextInt(10)));
            }
        }
        return sb.toString();
    }
}
