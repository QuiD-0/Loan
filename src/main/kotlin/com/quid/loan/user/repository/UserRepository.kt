package com.quid.loan.user.repository

import com.quid.loan.user.domain.User
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface UserRepository {
    fun createUser(user: User): User
    fun checkNicknameDuplicate(nickname: String) : Boolean
    fun findById(userId: Long): User

    @Repository
    class UserRepositoryImpl(private val userJpaRepository: UserJpaRepository) : UserRepository {
        override fun createUser(user: User): User {
            return userJpaRepository.save(user)
        }

        override fun checkNicknameDuplicate(nickname: String): Boolean {
            return userJpaRepository.existsByNickname(nickname)
        }

        override fun findById(userId: Long): User {
            return userJpaRepository.findByIdOrNull(userId) ?: fail(StatusCode.USER_NOT_FOUND_ERROR)
        }

    }
}