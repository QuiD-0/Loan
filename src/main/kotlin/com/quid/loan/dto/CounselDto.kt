package com.quid.loan.dto

import com.quid.loan.domain.Counsel
import java.time.LocalDateTime


data class CounselRequest constructor(
    val name: String,
    val phone: String,
    val email: String,
    val memo: String? = null,
    val address: String,
    val zip: String,
) {
    fun toEntity(): Counsel {
        return Counsel.of(
            name = name,
            phone = phone,
            email = email,
            memo = memo,
            address = address,
            zip = zip
        )
    }
}

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
