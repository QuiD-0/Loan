package com.quid.loan.dto

import com.quid.loan.domain.Counsel
import com.quid.loan.utils.ValidationPipe
import com.quid.loan.utils.Step
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
        if (input.email.isBlank()) fail("이메일을 입력해주세요.")
        val regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"
        if (!input.email.matches(regex.toRegex())) fail("이메일 형식이 올바르지 않습니다.")
        return input
    }
}

class PhoneCheck : Step<Counsel> {
    override fun process(input: Counsel): Counsel {
        if (input.phone.isBlank()) fail("전화 번호를 입력해주세요.")
        val regex = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$"
        if (!input.phone.matches(regex.toRegex())) fail("전화 번호 형식이 올바르지 않습니다.")
        return input
    }
}

class NameCheck : Step<Counsel> {
    override fun process(input: Counsel): Counsel {
        if (input.name.isBlank()) fail("이름을 입력해주세요.")
        return input
    }
}

