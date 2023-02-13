package com.quid.loan.loan.domain

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.loan.dto.LoanRequest
import com.quid.loan.user.domain.User
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "loan")
class Loan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne(mappedBy = "loan")
    val user: User,
    @OneToOne
    val counsel: Counsel,
    val amount: Double,
    val rate: Double,
    val expiredAt: LocalDateTime,
    var remain: Double = amount,
) {
    init {
        LoanValidator.validate(this)
    }

    fun pay(amount: Double) {
        if (remain < amount) fail(StatusCode.PAY_AMOUNT_IS_TOO_BIG_ERROR)
        remain -= amount
    }

    companion object {
        fun create(request: LoanRequest, user: User): Loan {
            return Loan(
                user = user,
                counsel = user.counsel!!,
                amount = request.amount,
                rate = request.rate,
                expiredAt = request.expiredAt,
            )
        }
    }
}