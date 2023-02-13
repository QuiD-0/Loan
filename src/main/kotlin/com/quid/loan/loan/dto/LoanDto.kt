package com.quid.loan.loan.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class LoanCreateRequest(
    val amount: Double,
    val rate: Double,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val expiredAt: LocalDate,
)