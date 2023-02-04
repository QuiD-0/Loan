package com.quid.loan.user.domain

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.user.repository.UserRepository
import javax.persistence.*

@Entity
@Table(
    name = "user",
    indexes = [
        Index(name = "idx_user_user_id", columnList = "user_id"),
        Index(name = "idx_user_nickname", columnList = "nickname"),
    ]
)
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userSeq: Long? = null,
    @Column(unique = true)
    val userId: Long,
    @Column(unique = true)
    var nickname: String,
    val email: String,
    val password: String,
    @OneToOne(mappedBy = "user")
    val counsel: Counsel? = null,
) {
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