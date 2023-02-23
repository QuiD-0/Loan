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
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    @OneToOne(fetch = FetchType.LAZY)
    val user: User,
    val phone: String,
    val email: String,
    val address: String,
    val zip: String,
    var memo: String? = null,
    @Enumerated(EnumType.STRING)
    var status: CounselStatus = CounselStatus.WAITING,
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

    fun process() {
        status = CounselStatus.PROCESSING
    }

    fun allow() {
        status = CounselStatus.ALLOWED
    }

    fun deny() {
        status = CounselStatus.DENIED
    }

    fun delete() {
        status = CounselStatus.DELETED
    }

    fun isAllowed(): Boolean {
        return status == CounselStatus.ALLOWED
    }
}