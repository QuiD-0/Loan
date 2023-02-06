package com.quid.loan.user.dto

import com.quid.loan.user.domain.User

data class UserCreateRequest(
    val userId: String,
    val email: String,
    val nickname: String = createRandomNickname(),
    val password: String,
)

fun createRandomNickname(): String {
    return "nickname" + (Math.random() * 100000).toInt()
}


data class UserResponse(
    val userId: String,
    val email: String,
    val nickname: String,
    val password: String,
){
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                userId = user.userId,
                email = user.email,
                nickname = user.nickname,
                password = user.password,
            )
        }
    }
}