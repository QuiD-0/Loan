package com.quid.loan.loan.service

import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.domain.LoanStatus
import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.loan.dto.PayRequest
import com.quid.loan.user.repository.UserRepository
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface LoanService {
    fun createLoan(userSeq: Long, request: LoanCreateRequest)
    fun pay(payRequest: PayRequest)

    @Service
    class LoanServiceImp(
        private val userRepository: UserRepository
    ) : LoanService {

        @Transactional
        override fun createLoan(userSeq: Long, request: LoanCreateRequest) {
            val user = userRepository.findById(userSeq)
            if (user.counsel == null) fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
            user.createLoan(request)
        }

        @Transactional
        override fun pay(payRequest: PayRequest) {
            val loan = userRepository.findById(payRequest.userSeq).loan
                ?: fail(StatusCode.LOAN_NOT_FOUND_ERROR)
            loan.pay(payRequest.amount)
            changeLoanStatus(loan)
        }

        private fun changeLoanStatus(loan: Loan) {
            if (loan.remain == 0.0) loan.complete()
            else loan.changeStatus(LoanStatus.PAYING)
        }
    }
}