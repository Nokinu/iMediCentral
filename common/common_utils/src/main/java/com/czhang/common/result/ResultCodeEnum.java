package com.czhang.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"OK"),
    FAIL(201, "Failed"),
    PARAM_ERROR( 202, "Wrong Parameters"),
    SERVICE_ERROR(203, "Service Exception"),
    DATA_ERROR(204, "Data Exception"),
    DATA_UPDATE_ERROR(205, "Data Version Exception"),

    LOGIN_AUTH(208, "Needs Login"),
    PERMISSION(209, "No permission"),

    CODE_ERROR(210, "Wrong Code"),
    //    LOGIN_MOBLE_ERROR(211, "账号不正确"),
    LOGIN_DISABLED_ERROR(212, "User Locked"),
    REGISTER_MOBLE_ERROR(213, "Phone Used"),
    //LOGIN_AURH(214, "需要登录"),
    //LOGIN_ACL(215, "没有权限"),

    URL_ENCODE_ERROR( 216, "URL Encoding Failed"),
    ILLEGAL_CALLBACK_REQUEST_ERROR( 217, "ILLEGAL_CALLBACK_REQUEST_ERROR"),
    FETCH_ACCESSTOKEN_FAILD( 218, "FETCH_ACCESSTOKEN_FAILD"),
    FETCH_USERINFO_ERROR( 219, "FETCH_USERINFO_ERROR"),
    //LOGIN_ERROR( 23005, "登录失败"),

    PAY_RUN(220, "PAY_RUN"),
    CANCEL_ORDER_FAIL(225, "CANCEL_ORDER_FAIL"),
    CANCEL_ORDER_NO(225, "CANCEL_ORDER_NO"),

    HOSCODE_EXIST(230, "HOSCODE_EXIST"),
    NUMBER_NO(240, "NUMBER_NO"),
    TIME_NO(250, "TIME_NO"),

    SIGN_ERROR(300, "SIGN_ERROR"),
    HOSPITAL_OPEN(310, "HOSPITAL_OPEN"),
    HOSPITAL_LOCK(320, "HOSPITAL_LOCK"),;

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
