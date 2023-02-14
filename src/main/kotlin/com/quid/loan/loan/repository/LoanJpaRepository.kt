package com.quid.loan.loan.repository

import com.quid.loan.loan.domain.Loan
import org.springframework.data.jpa.repository.JpaRepository

interface LoanJpaRepository : JpaRepository<Loan, Long>