package com.quid.loan.counsel.domain

import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.user.domain.User
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(
    name = "counsel",
    indexes = [
        Index(name = "idx_counsel_phone", columnList = "phone"),
    ]
)
@EntityListeners(AuditingEntityListener::class)
class Counsel(
    @Id @GeneratedValue(strategy = IDENTITY)
    val counselId: Long? = null,
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "counsel")
    val user: User,
    val phone: String,
    val email: String,
    val address: String,
    val zip: String,
    var memo: String? = null,
    @CreatedDate
    var createAt: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate
    var updateAt: LocalDateTime = LocalDateTime.MIN,
) {
    init {
        CounselValidator.validate(this)
    }

    fun updateMemo(memo: String) {
        this.memo = memo
    }

    companion object {
        fun of(request: CounselRequest, user: User): Counsel {
            return Counsel(
                user = user,
                phone = request.phone,
                email = request.email,
                memo = request.memo,
                address = request.address,
                zip = request.zip,
            )
        }
    }
}