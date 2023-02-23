package com.quid.loan.loan.repository

import com.quid.loan.loan.domain.Loan
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface LoanJpaRepository : JpaRepository<Loan, Long>{
    fun findByExpiredAtBefore(expiredAt: LocalDate): List<Loan>
}