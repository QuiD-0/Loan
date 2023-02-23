package com.quid.loan.loan.service

import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.domain.LoanStatus
import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.loan.dto.LoanResponse
import com.quid.loan.loan.dto.PayRequest
import com.quid.loan.loan.repository.LoanRepository
import com.quid.loan.user.domain.User
import com.quid.loan.user.repository.UserRepository
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface LoanService {
    fun createLoan(userSeq: Long, request: LoanCreateRequest)
    fun pay(payRequest: PayRequest)
    fun getHistories(): Map<String, List<LoanResponse>>
    fun getPayedAmount(): Map<String, String>

    @Service
    class LoanServiceImp(
        private val userRepository: UserRepository,
        private val loanRepository: LoanRepository
    ) : LoanService {

        @Transactional
        override fun createLoan(userSeq: Long, request: LoanCreateRequest) {
            val user = userRepository.findById(userSeq)
            checkAvailableLoan(user)
            request.toLoan(user).also { user.createLoan(it) }
        }

        private fun checkAvailableLoan(user: User) {
            if (user.counsel == null) fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
            if (user.loan != null) fail(StatusCode.LOAN_ALREADY_EXIST_ERROR)
        }

        @Transactional
        override fun pay(payRequest: PayRequest) {
            val loan = userRepository.findLoanByUserId(payRequest.userSeq)
            loan.pay(payRequest.amount)
            changeLoanStatus(loan)
        }

        @Transactional(readOnly = true)
        override fun getHistories(): Map<String, List<LoanResponse>> {
            return loanRepository.getHistories()
        }

        @Transactional(readOnly = true)
        override fun getPayedAmount(): Map<String, String> {
            return loanRepository.findPayedAmount()
        }

        private fun changeLoanStatus(loan: Loan) {
            if (loan.remain == 0.0) loan.complete()
            else loan.changeStatus(LoanStatus.PAYING)
        }
    }
}