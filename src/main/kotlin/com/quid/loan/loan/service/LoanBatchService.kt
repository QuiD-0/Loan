package com.quid.loan.loan.service

import com.quid.loan.loan.repository.LoanRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

interface LoanBatchService {
    fun calculateInterest()

    @Component
    class LoanBatchServiceImpl(private val loanRepository: LoanRepository) : LoanBatchService {

        @Scheduled(cron = "0 0 0 1 * *")
        override fun calculateInterest() {
            loanRepository.findAll().forEach { it.calculateInterest() }
        }
    }
}