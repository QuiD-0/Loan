package com.quid.loan.utils

enum class StatusCode (val message : String) {
    COUNSEL_NOT_FOUND_ERROR("상담이 존재하지 않습니다."),
    EMAIL_NOT_VALID_ERROR("이메일 형식이 올바르지 않습니다."),
    EMAIL_NEED_VALUE_ERROR("이메일을 입력해주세요."),
    PHONE_NEED_VALUE_ERROR("전화 번호를 입력해주세요."),
    PHONE_NOT_VALID_ERROR("전화 번호 형식이 올바르지 않습니다."),
    NICKNAME_NEED_VALUE_ERROR("닉네임을 입력해주세요."),
    NICKNAME_IS_TOO_LONG_ERROR("닉네임은 10자 이하로 입력해주세요."),
    PASSWORD_NEED_VALUE_ERROR("비밀번호를 입력해주세요."),
    PASSWORD_IS_TOO_SHORT_ERROR("비밀번호는 8자 이상으로 입력해주세요."),
    PASSWORD_IS_TOO_LONG_ERROR("비밀번호는 20자 이하로 입력해주세요."),
    USER_NOT_FOUND_ERROR("회원이 존재하지 않습니다."),
    COUNSEL_ALREADY_EXIST_ERROR("이미 존재하는 상담입니다."),
    PAY_AMOUNT_IS_TOO_BIG_ERROR("상환 금액이 대출 금액보다 큽니다."),
    LOAN_AMOUNT_IS_TOO_BIG_ERROR("대출 금액이 너무 큽니다."),
    LOAN_RATE_IS_TOO_BIG_ERROR("대출 금리가 너무 큽니다."),
    COUNSEL_IS_NOT_COMPLETE_ERROR("상담이 완료되지 않았습니다."),
    COUNSEL_NOT_ALLOWED_ERROR("대출이 허용되지 않았습니다."),
    LOAN_NOT_FOUND_ERROR("대출이 존재하지 않습니다."),
    LOAN_ALREADY_EXIST_ERROR("이미 대출이 존재합니다."),
}