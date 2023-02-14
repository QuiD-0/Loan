package com.quid.loan.loan.repository

import com.quid.loan.loan.domain.Loan
import org.springframework.stereotype.Repository

interface LoanRepository {
    fun findAll(): List<Loan>

    @Repository
    class LoanRepositoryImp(private val loanJpaRepository: LoanJpaRepository) : LoanRepository {
        override fun findAll(): List<Loan> {
            return loanJpaRepository.findAll().filter { it.isPaying() }
        }
    }
}