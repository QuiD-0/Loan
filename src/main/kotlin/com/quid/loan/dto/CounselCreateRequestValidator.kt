package com.quid.loan.dto

import com.quid.loan.utils.Pipeline
import com.quid.loan.utils.Step
import com.quid.loan.utils.fail

class CounselCreateRequestValidator {
    companion object {
        fun validate(counselRequest: CounselRequest) {
            Pipeline(NameCheck())
                .pipe(PhoneCheck())
                .execute(counselRequest)
        }
    }

}

class PhoneCheck : Step<CounselRequest, CounselRequest> {
    override fun process(input: CounselRequest): CounselRequest {
        if (input.phone.isBlank()) fail("phone is blank")
        return input
    }
}

class NameCheck : Step<CounselRequest, CounselRequest> {
    override fun process(input: CounselRequest): CounselRequest {
        if (input.name.isBlank()) fail("name is blank")
        return input
    }
}