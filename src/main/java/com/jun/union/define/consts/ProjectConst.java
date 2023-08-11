package com.jun.union.define.consts;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectConst {

    public static final String TOKEN_NAME = "X-AUTH-TOKEN";
    public static final String LOGOUT = "LOGOUT";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PASSWORD_REGEX = "^(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\-/])(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9!@#$%^&*()_+{}\\[\\]:;<>,.?~\\-/]{8,}$";
}
