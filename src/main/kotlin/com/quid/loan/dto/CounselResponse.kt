package com.quid.loan.dto

import java.time.LocalDateTime

data class CounselResponse(
    val counselId: Long,
    val name: String,
    val phone: String,
    val email: String,
    val memo: String?,
    val address: String,
    val zip: String,
    val appliedAt: LocalDateTime
)
