package com.quid.loan.dto

import com.quid.loan.domain.Counsel
import java.time.LocalDateTime


data class CounselRequest (
    val name: String,
    val phone: String,
    val email: String,
    val memo: String? = null,
    val address: String,
    val zip: String,
){
    init {
        CounselCreateRequestValidator.validate(this)
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
) {
    companion object {
        fun of(counsel: Counsel): CounselResponse {
            return CounselResponse(
                counselId = counsel.counselId?: 0,
                name = counsel.name,
                phone = counsel.phone,
                email = counsel.email,
                memo = counsel.memo,
                address = counsel.address,
                zip = counsel.zip,
                appliedAt = counsel.createdAt
            )
        }
    }
}

data class CounselUpdateRequest(
    val counselId: Long,
    val memo: String
)