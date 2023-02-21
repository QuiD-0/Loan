package com.quid.loan.service

import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.counsel.service.CounselService
import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.loan.service.LoanService
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.user.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class ServiceDataInit {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var counselService: CounselService

    @Autowired
    private lateinit var loanService: LoanService

    @Test
    fun createUser() {
        val userCreateRequest = UserCreateRequest(
            userId = "quid",
            email = "quid@naver.com",
            password = "testPassword",
            nickname = "quid"
        )

        val userResponse = userService.createUser(userCreateRequest)

        assertEquals(userResponse.userId, userCreateRequest.userId)
    }

    @Test
    fun createCounsel() {
        val counselCreateRequest = CounselRequest(
            userSeq = 1,
            phone = "010-1234-5678",
            email = "quid@naver.com",
            address = "서울시 강남구",
            zip = "12345"
        )

        counselService.createCounsel(counselCreateRequest)
        val counsel = counselService.getCounsel(1L)

        assertEquals(counsel.address, counselCreateRequest.address)
    }

    @Test
    fun createLoan() {
        userService.allowCounsel(1)

        val loanCreateRequest = LoanCreateRequest(
            amount = 1000000.0,
            rate = 0.1,
            expiredAt = LocalDate.now().plusDays(30)
        )

        loanService.createLoan(1, loanCreateRequest)
    }
}