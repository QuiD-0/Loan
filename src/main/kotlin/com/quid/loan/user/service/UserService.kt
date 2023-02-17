package com.quid.loan.user.service

import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.user.domain.User
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.user.dto.UserResponse
import com.quid.loan.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserService {

    fun createUser(request: UserCreateRequest): UserResponse
    fun getUserCounsel(id: Long): CounselResponse
    fun allowCounsel(id: Long)

    @Service
    class UserServiceImpl(private val userRepository: UserRepository) : UserService {
        private val logger = mu.KotlinLogging.logger {}

        @Transactional
        override fun createUser(request: UserCreateRequest): UserResponse {
            logger.info { "createUser: $request" }
            userRepository.checkNicknameDuplicate(request.nickname)
            return userRepository.createUser(User.of(request))
                .let { UserResponse.of(it) }
        }

        @Transactional(readOnly = true)
        override fun getUserCounsel(id: Long): CounselResponse {
            return userRepository.findCounselByUserId(id)
                .let { CounselResponse.of(it) }
        }

        @Transactional
        override fun allowCounsel(id: Long) {
            userRepository.findById(id).allowCounsel()
        }
    }
}