package com.quid.loan.loan.service

import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.loan.repository.LoanRepository
import com.quid.loan.user.repository.UserRepository
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface LoanService {
    fun createLoan(userSeq: Long, request: LoanCreateRequest)

    @Service
    class LoanServiceImp(
        private val loanRepository: LoanRepository,
        private val userRepository: UserRepository
    ) : LoanService {

        @Transactional
        override fun createLoan(userSeq: Long, request: LoanCreateRequest) {
            val user = userRepository.findById(userSeq)
            if (user.counsel == null) fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
            val loan = Loan.create(request, user)
            user.loan = loan
        }
    }
}