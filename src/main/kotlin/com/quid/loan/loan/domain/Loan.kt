package com.quid.loan.loan.domain

import com.quid.loan.user.domain.User
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
    val amount: Double,
    val rate: Double,
    val expiredAt: LocalDateTime,
    private var remain: Double,
) {
    fun pay(amount: Double) {
        remain -= amount
    }
}