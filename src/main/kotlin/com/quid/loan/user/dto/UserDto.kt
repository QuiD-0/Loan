package com.quid.loan.user.dto

data class UserCreateRequest(
    val userId: Long,
    val email: String,
    val nickname: String = createRandomNickname(),
    val password: String,
)

fun createRandomNickname(): String {
    return "nickname" + (Math.random() * 100000).toInt()
}
