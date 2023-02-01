package com.quid.loan.service

import com.quid.loan.dto.CounselRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CounselServiceImplTest {

    @Autowired
    private lateinit var counselService: CounselService

    @Test
    fun createTest() {
        val counselRequest = CounselRequest(
            name = "name",
            phone = "phoneNumber",
            memo = "memo",
            address = "address",
            zip = "zip",
            email = "email"
        )

        val counselResponse = counselService.createCounsel(counselRequest)

        assert(counselResponse.name == counselRequest.name)
    }

}