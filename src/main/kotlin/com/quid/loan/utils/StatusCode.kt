package com.quid.loan.utils

enum class StatusCode (val message : String) {
    COUNSEL_DUPLICATE_ERROR("이미 존재하는 상담입니다."),
    COUNSEL_NOT_FOUND_ERROR("상담이 존재하지 않습니다."),
    EMAIL_NOT_VALID_ERROR("이메일 형식이 올바르지 않습니다."),
    EMAIL_NEED_VALUE_ERROR("이메일을 입력해주세요."),
    PHONE_NEED_VALUE_ERROR("전화 번호를 입력해주세요."),
    PHONE_NOT_VALID_ERROR("전화 번호 형식이 올바르지 않습니다."),
    NAME_NEED_VALUE_ERROR("이름을 입력해주세요.")
}