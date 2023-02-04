package com.quid.loan.user.repository

import com.quid.loan.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {
    fun existsByNickname(nickname: String): Boolean
}