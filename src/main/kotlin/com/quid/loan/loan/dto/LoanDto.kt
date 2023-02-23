package com.quid.loan.loan.dto

import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.domain.LoanStatus
import com.quid.loan.user.domain.User
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class LoanCreateRequest(
    val amount: Double,
    val rate: Double,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val expiredAt: LocalDate,
){
    fun toLoan(user: User): Loan {
        return Loan(
            user = user,
            counsel = user.counsel!!,
            amount = amount,
            rate = rate,
            expiredAt = expiredAt,
        )
    }
}

class PayRequest(
    val userSeq: Long,
    val amount: Double,
)

class LoanResponse(
    val name: String,
    val amount: Double,
    val rate: Double,
    val expiredAt: LocalDate,
    val remain: Double,
    val loanStatus: LoanStatus,
) {
    companion object {
        fun of(loan: Loan): LoanResponse {
            return LoanResponse(
                loan.user.nickname,
                loan.amount,
                loan.rate,
                loan.expiredAt,
                loan.remain,
                loan.loanStatus
            )
        }
    }
}

class UnpaidLoanResponse(
    val name: String,
    val userId: String,
    val amount: Double,
    val rate: Double,
    val expiredAt: LocalDate,
    val remain: Double,
    val loanStatus: LoanStatus,
) {
    companion object {
        fun of(loan: Loan): UnpaidLoanResponse {
            return UnpaidLoanResponse(
                loan.user.nickname,
                loan.user.userId,
                loan.amount,
                loan.rate,
                loan.expiredAt,
                loan.remain,
                loan.loanStatus
            )
        }
    }
}