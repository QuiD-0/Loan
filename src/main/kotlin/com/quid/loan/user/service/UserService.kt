package com.quid.loan.user.service

import com.quid.loan.user.domain.User
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserService {

    fun createUser(request: UserCreateRequest): User

    @Service
    class UserServiceImpl(private val userRepository: UserRepository) : UserService {
        private val logger = mu.KotlinLogging.logger {}

        @Transactional
        override fun createUser(request: UserCreateRequest): User {
            logger.info { "createUser: $request" }
            userRepository.checkNicknameDuplicate(request.nickname)
            return userRepository.createUser(User.of(request))
        }
    }
}