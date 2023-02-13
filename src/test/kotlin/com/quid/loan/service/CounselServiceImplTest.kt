package com.quid.loan.service

import com.quid.loan.user.repository.UserRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class CounselServiceImplTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    @Transactional
    fun test() {
        val user = userRepository.findById(1)
        val counsel = user.counsel
    }

}