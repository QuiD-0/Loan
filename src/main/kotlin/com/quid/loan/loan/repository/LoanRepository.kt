package com.quid.loan.loan.repository

import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.dto.LoanResponse
import org.springframework.stereotype.Repository
import java.time.LocalDate

interface LoanRepository {
    fun findAllPayingLoan(): List<Loan>
    fun getHistories(): Map<String, List<LoanResponse>>
    fun findPayedAmount(): Map<String, String>
    fun findAll(): List<Loan>
    fun findUnpaidLoans(): List<Loan>

    @Repository
    class LoanRepositoryImp(private val loanJpaRepository: LoanJpaRepository) : LoanRepository {
        override fun findAllPayingLoan(): List<Loan> {
            return loanJpaRepository.findAll().filter { it.isPaying() }
        }

        override fun getHistories(): Map<String, List<LoanResponse>> {
            return loanJpaRepository.findAll().map(LoanResponse.Companion::of).groupBy { it.name }
        }

        override fun findPayedAmount(): Map<String, String> {
            return loanJpaRepository.findAll().map { it.user.nickname to it.amount }
                .groupBy { it.first }
                .mapValues { it.value.sumOf { it.second }.toString() + "원" }
        }

        override fun findAll(): List<Loan> {
            return loanJpaRepository.findAll()
        }

        override fun findUnpaidLoans(): List<Loan> {
            return loanJpaRepository.findByExpiredAtBefore(LocalDate.now()).filter { it.isNotComplete() }
        }
    }
}