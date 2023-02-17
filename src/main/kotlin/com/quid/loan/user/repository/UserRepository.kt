package com.quid.loan.user.repository

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.loan.domain.Loan
import com.quid.loan.user.domain.User
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.StatusCode.COUNSEL_NOT_FOUND_ERROR
import com.quid.loan.utils.StatusCode.USER_NOT_FOUND_ERROR
import com.quid.loan.utils.fail
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface UserRepository {
    fun createUser(user: User): User
    fun checkNicknameDuplicate(nickname: String): Boolean
    fun findById(userId: Long): User
    fun deleteById(id: Long)
    fun findCounselByUserId(userId: Long): Counsel
    fun findLoanByUserId(userId: Long): Loan

    @Repository
    class UserRepositoryImpl(private val userJpaRepository: UserJpaRepository) : UserRepository {
        override fun createUser(user: User): User {
            return userJpaRepository.save(user)
        }

        override fun checkNicknameDuplicate(nickname: String): Boolean {
            return userJpaRepository.existsByNickname(nickname)
        }

        override fun findById(userId: Long): User {
            return userJpaRepository.findByIdOrNull(userId) ?: fail(USER_NOT_FOUND_ERROR)
        }

        override fun deleteById(id: Long) {
            userJpaRepository.findByIdOrNull(id)?.let { userJpaRepository.delete(it) }
                ?: fail(USER_NOT_FOUND_ERROR)
        }

        override fun findCounselByUserId(userId: Long): Counsel {
            return userJpaRepository.findByIdOrNull(userId)?.counsel
                ?: fail(COUNSEL_NOT_FOUND_ERROR)
        }

        override fun findLoanByUserId(userId: Long): Loan {
            return userJpaRepository.findByIdOrNull(userId)?.loan
                ?: fail(StatusCode.LOAN_NOT_FOUND_ERROR)
        }

    }
}