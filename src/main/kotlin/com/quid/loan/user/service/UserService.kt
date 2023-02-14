package com.quid.loan.user.service

import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.user.domain.User
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.user.dto.UserResponse
import com.quid.loan.user.repository.UserRepository
import com.quid.loan.utils.StatusCode.COUNSEL_NOT_FOUND_ERROR
import com.quid.loan.utils.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserService {

    fun createUser(request: UserCreateRequest): UserResponse
    fun getUserCounsel(id: Long): CounselResponse

    @Service
    class UserServiceImpl(private val userRepository: UserRepository) : UserService {
        private val logger = mu.KotlinLogging.logger {}

        @Transactional
        override fun createUser(request: UserCreateRequest): UserResponse {
            logger.info { "createUser: $request" }
            userRepository.checkNicknameDuplicate(request.nickname)
            val createUser = userRepository.createUser(User.of(request))
            return UserResponse.of(createUser)
        }

        @Transactional(readOnly = true)
        override fun getUserCounsel(id: Long): CounselResponse {
            return userRepository.findById(id).counsel?.let { CounselResponse.of(it) }
                ?: fail(COUNSEL_NOT_FOUND_ERROR)
        }
    }
}