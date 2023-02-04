package com.quid.loan.user.repository

import com.quid.loan.user.domain.User
import org.springframework.stereotype.Repository

interface UserRepository {
    fun createUser(user: User): User
    fun checkNicknameDuplicate(nickname: String) : Boolean

    @Repository
    class UserRepositoryImpl(private val userJpaRepository: UserJpaRepository) : UserRepository {
        override fun createUser(user: User): User {
            return userJpaRepository.save(user)
        }

        override fun checkNicknameDuplicate(nickname: String): Boolean {
            return userJpaRepository.existsByNickname(nickname)
        }

    }
}