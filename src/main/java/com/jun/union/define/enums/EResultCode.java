package com.jun.union.define.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@RequiredArgsConstructor
@Slf4j
public enum EResultCode {

    // 성공
    SUCCESS("0", "SUCCESS"),

    // 실패
    FAIL("1", "FAIL"),

    // 회원가입 실패
    REGISTER_FAIL_ALREADY_EMAIL("E101", "이미 등록된 이메일 입니다."),
    REGISTER_FAIL_FORMAT_EMAIL("E102", "이메일 형식이 아닙니다."),
    REGISTER_FAIL_FORMAT_PASSWORD("E103", "비밀번호는 문자와 숫자를 포함한 8자리 이상, 특수 문자가 1개 이상 포함 되어야 합니다."),
    REGISTER_FAIL_NO_NAME("E104", "이름을 입력해 주세요."),

    // 로그인 실패
    LOGIN_FAIL_NOT_FIND_USER("E105", "이메일 또는 비밀번호가 일치하지 않습니다."),
    LOGIN_FAIL_WRONG_PASSWORD("E106", "이메일 또는 비밀번호가 일치하지 않습니다."),

    // 비밀번호 변경
    CHANGE_PASSWORD_FAIL_NOT_FIND_USER("E107", "존재하지 않는 회원입니다."),
    CHANGE_PASSWORD_FAIL_WRONG_PASSWORD("E108", "비밀번호가 올바르지 않습니다."),
    CHANGE_PASSWORD_FAIL_FORMAT_PASSWORD("E109", "비밀번호는 문자와 숫자를 포함한 8자리 이상, 특수 문자가 1개 이상 포함 되어야 합니다."),

    // 임시 비밀번호 발급
    ISSUE_TEMPORARY_PASSWORD_FAIL_NOT_FIND_USER("E110", "존재하지 않는 회원입니다.");

    private final String code;
    private final String message;

}
