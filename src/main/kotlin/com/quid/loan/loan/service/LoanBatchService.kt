package com.quid.loan.loan.service

import com.quid.loan.loan.repository.LoanRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface LoanBatchService {
    fun calculateInterest()

    @Component
    class LoanBatchServiceImpl(private val loanRepository: LoanRepository) : LoanBatchService {

        @Transactional
        @Scheduled(cron = "0 0 0 1 * *")
        override fun calculateInterest() {
            loanRepository.findAllPayingLoan().forEach {
                it.calculateInterest()
            }
        }
    }
}