package com.quid.loan.user.domain

import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.Step
import com.quid.loan.utils.ValidationPipe
import com.quid.loan.utils.fail

class UserValidator {
    companion object {
        fun validate(user: User) {
            ValidationPipe(NickNameCheck())
                .addPipe(EmailCheck())
                .addPipe(PasswordCheck())
                .execute(user)
        }
    }

}

class PasswordCheck : Step<User> {
    override fun process(input: User): User {
        if (input.password.isBlank()) fail(StatusCode.PASSWORD_NEED_VALUE_ERROR)
        if (input.password.length < 8) fail(StatusCode.PASSWORD_IS_TOO_SHORT_ERROR)
        if (input.password.length > 20) fail(StatusCode.PASSWORD_IS_TOO_LONG_ERROR)
        return input
    }
}

class EmailCheck : Step<User> {
    override fun process(input: User): User {
        if (input.email.isBlank()) fail(StatusCode.EMAIL_NEED_VALUE_ERROR)
        val regex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"
        if (!input.email.matches(regex.toRegex())) fail(StatusCode.EMAIL_NOT_VALID_ERROR)
        return input
    }
}

class NickNameCheck : Step<User> {
    override fun process(input: User): User {
        if (input.nickname.isBlank()) fail(StatusCode.NICKNAME_NEED_VALUE_ERROR)
        if (input.nickname.length > 10) fail(StatusCode.NICKNAME_IS_TOO_LONG_ERROR)
        return input
    }
}
