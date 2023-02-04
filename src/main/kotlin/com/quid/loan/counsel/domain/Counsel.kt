package com.quid.loan.counsel.domain

import com.quid.loan.counsel.dto.CounselRequest
import org.hibernate.annotations.Where
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@Where(clause = "deleted_at is null")
@Table(
    name = "counsel",
    indexes = [
        Index(name = "idx_counsel_name", columnList = "name"),
        Index(name = "idx_counsel_phone", columnList = "phone"),
    ]
)
@EntityListeners(AuditingEntityListener::class)
class Counsel(
    @Id @GeneratedValue(strategy = IDENTITY)
    val counselId: Long? = null,
    val name: String,
    val phone: String,
    val email: String,
    val address: String,
    val zip: String,
    var memo: String? = null,
    @CreatedDate
    var createAt: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate
    var updateAt: LocalDateTime = LocalDateTime.MIN,
    var deletedAt: LocalDateTime? = null,
) {
    init {
        CounselValidator.validate(this)
    }

    fun updateMemo(memo: String) {
        this.memo = memo
    }

    companion object {
        fun of(request: CounselRequest): Counsel {
            return Counsel(
                name = request.name,
                phone = request.phone,
                email = request.email,
                memo = request.memo,
                address = request.address,
                zip = request.zip,
            )
        }
    }
}