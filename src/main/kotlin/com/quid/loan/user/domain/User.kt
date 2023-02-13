package com.quid.loan.user.domain

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.loan.domain.Loan
import com.quid.loan.user.dto.UserCreateRequest
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
    private var nickname: String,
    val email: String,
    val password: String,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var counsel: Counsel? = null,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var loan: Loan? = null,
) {
    fun deleteCounsel() {
        counsel = null
    }

    fun createCounsel(request: CounselRequest): Counsel {
        return Counsel.of(request, this).also {
            counsel = it
        }
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