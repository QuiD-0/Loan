package com.quid.loan.counsel.dto

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.user.domain.User
import java.time.LocalDateTime


data class CounselRequest(
    val userSeq: Long,
    val phone: String,
    val email: String,
    val memo: String? = null,
    val address: String,
    val zip: String
) {
    fun toCounsel(user: User): Counsel {
        return Counsel(
            user = user,
            phone = phone,
            email = email,
            memo = memo,
            address = address,
            zip = zip,
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
) {
    companion object {
        fun of(counsel: Counsel): CounselResponse {
            return CounselResponse(
                counselId = counsel.id ?: 0,
                name = counsel.user.nickname,
                phone = counsel.phone,
                email = counsel.email,
                memo = counsel.memo,
                address = counsel.address,
                zip = counsel.zip,
                appliedAt = counsel.createAt
            )
        }
    }
}

data class CounselUpdateRequest(
    val userSeq: Long,
    val memo: String
)