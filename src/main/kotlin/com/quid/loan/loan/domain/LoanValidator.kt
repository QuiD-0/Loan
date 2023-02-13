package com.quid.loan.loan.domain

import com.quid.loan.counsel.domain.CounselStatus
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.Step
import com.quid.loan.utils.ValidationPipe
import com.quid.loan.utils.fail

class LoanValidator {
    companion object {
        fun validate(loan: Loan) {
            ValidationPipe(LoanAmountCheck())
                .addPipe(CounselStatusCheck())
                .addPipe(LoanRateCheck())
                .execute(loan)
        }
    }
}

class CounselStatusCheck : Step<Loan> {
    override fun process(input: Loan): Loan {
        if (input.counsel.status != CounselStatus.COMPLETE) fail(StatusCode.COUNSEL_IS_NOT_COMPLETE_ERROR)
        return input
    }
}

class LoanRateCheck : Step<Loan> {
    override fun process(input: Loan): Loan {
        if (input.rate > 10) fail(StatusCode.LOAN_RATE_IS_TOO_BIG_ERROR)
        return input
    }

}

class LoanAmountCheck : Step<Loan> {
    override fun process(input: Loan): Loan {
        if (input.amount > 1_000_000_000) fail(StatusCode.LOAN_AMOUNT_IS_TOO_BIG_ERROR)
        return input
    }
}



