package com.quid.loan.loan.service

import com.quid.loan.loan.repository.LoanRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface LoanScheduleService {
    fun calculateInterest()

    @Component
    class LoanScheduleServiceImpl(private val loanRepository: LoanRepository) : LoanScheduleService {

        @Transactional
        @Scheduled(cron = "0 0 0 1 * *")
        override fun calculateInterest() {
            loanRepository.findAllPayingLoan().forEach {
                it.calculateInterest()
            }
        }
    }
}