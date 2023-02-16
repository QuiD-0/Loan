package com.quid.loan.loan.repository

import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.dto.LoanResponse
import org.springframework.stereotype.Repository

interface LoanRepository {
    fun findAllPayingLoan(): List<Loan>
    fun getHistories(): Map<String, List<LoanResponse>>

    @Repository
    class LoanRepositoryImp(private val loanJpaRepository: LoanJpaRepository) : LoanRepository {
        override fun findAllPayingLoan(): List<Loan> {
            return loanJpaRepository.findAll().filter { it.isPaying() }
        }

        override fun getHistories(): Map<String, List<LoanResponse>> {
            return loanJpaRepository.findAll().map(LoanResponse.Companion::of).groupBy { it.name }
        }
    }
}