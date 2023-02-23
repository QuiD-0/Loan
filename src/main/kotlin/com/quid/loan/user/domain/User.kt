package com.quid.loan.user.domain

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.counsel.domain.CounselStatus
import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.loan.domain.Loan
import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import javax.persistence.*

@Entity
@Table(
    name = "user",
    indexes = [
        Index(name = "idx_user_user_id", columnList = "userId"),
        Index(name = "idx_user_nickname", columnList = "nickname"),
    ]
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userSeq: Long? = null,
    @Column(unique = true)
    val userId: String,
    @Column(unique = true)
    var nickname: String,
    val email: String,
    val password: String,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var counsel: Counsel? = null,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var loan: Loan? = null,
) {
    fun deleteCounsel() {
        counsel?.delete()
            .also { counsel = null }
    }

    fun createCounsel(request: CounselRequest): Counsel {
        return request.toCounsel(this)
            .also { counsel = it }
    }

    fun createLoan(request: LoanCreateRequest): Loan {
        if (isNotAllowed()) fail(StatusCode.COUNSEL_NOT_ALLOWED_ERROR)
        return request.toLoan(this)
            .also { loan = it }
    }

    private fun isNotAllowed(): Boolean {
        this.counsel?.let {
            return it.status != CounselStatus.ALLOWED
        } ?: fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
    }

    fun allowCounsel() {
        counsel?.allow() ?: fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
    }

    fun completeLoan() {
        loan = null
        counsel = null
    }

    fun hasCounsel(): Boolean {
        return counsel != null
    }

    init {
        UserValidator.validate(this)
    }

    companion object {
        fun of(request: UserCreateRequest): User {
            return User(
                userId = request.userId,
                nickname = request.nickname,
                email = request.email,
                password = request.password,
            )
        }
    }
}