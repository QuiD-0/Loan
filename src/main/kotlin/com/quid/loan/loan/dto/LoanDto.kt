package com.quid.loan.loan.dto

import java.time.LocalDateTime

class LoanRequest(
    val amount: Double,
    val rate: Double,
    val expiredAt: LocalDateTime,
)