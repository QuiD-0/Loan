package com.quid.loan.loan.domain

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.loan.domain.LoanStatus.WAITING
import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.user.domain.User
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "loan")
class Loan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne(fetch = FetchType.LAZY)
    val user: User,
    @OneToOne(fetch = FetchType.LAZY)
    val counsel: Counsel,
    val amount: Double,
    val rate: Double,
    val expiredAt: LocalDate,
    var remain: Double = amount,
    @Enumerated(EnumType.STRING)
    var loanStatus: LoanStatus = WAITING,
) {
    init {
        LoanValidator.validate(this)
    }

    fun pay(amount: Double) {
        if (remain < amount) fail(StatusCode.PAY_AMOUNT_IS_TOO_BIG_ERROR)
        remain -= amount
    }

    fun isPaying(): Boolean {
        return loanStatus == LoanStatus.PAYING
    }

    fun calculateInterest() {
        this.remain += remain * rate
    }

    fun complete() {
        loanStatus = LoanStatus.COMPLETE
        user.completeLoan()
    }

    fun changeStatus(status: LoanStatus) {
        loanStatus = status
    }

    fun isWaiting(): Boolean {
        return loanStatus == WAITING
    }
}