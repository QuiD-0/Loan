package com.quid.loan.dto

import com.quid.loan.domain.Counsel
import com.quid.loan.utils.StatusCode.*
import com.quid.loan.utils.Step
import com.quid.loan.utils.ValidationPipe
import com.quid.loan.utils.fail

class CounselValidator {
    companion object {
        fun validate(counsel: Counsel) {
            ValidationPipe(NameCheck())
                .addPipe(PhoneCheck())
                .addPipe(EmailCheck())
                .execute(counsel)
        }
    }
}

class EmailCheck : Step<Counsel> {
    override fun process(input: Counsel): Counsel {
        if (input.email.isBlank()) fail(EMAIL_NEED_VALUE_ERROR)
        val regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"
        if (!input.email.matches(regex.toRegex())) fail(EMAIL_NOT_VALID_ERROR)
        return input
    }
}

class PhoneCheck : Step<Counsel> {
    override fun process(input: Counsel): Counsel {
        if (input.phone.isBlank()) fail(PHONE_NEED_VALUE_ERROR)
        val regex = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$"
        if (!input.phone.matches(regex.toRegex())) fail(PHONE_NOT_VALID_ERROR)
        return input
    }
}

class NameCheck : Step<Counsel> {
    override fun process(input: Counsel): Counsel {
        if (input.name.isBlank()) fail(NAME_NEED_VALUE_ERROR)
        return input
    }
}

