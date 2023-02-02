package com.quid.loan.domain

import com.quid.loan.dto.CounselRequest
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
@Where(clause = "deleted_at is null")
class Counsel(
    @Id @GeneratedValue(strategy = IDENTITY) val counselId: Long? = null,
    val name: String,
    val phone: String,
    val email: String,
    val address: String,
    val zip: String,
    var memo: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    var deletedAt: LocalDateTime? = null
) {
    fun updateMemo(memo: String) {
        this.memo = memo
        this.updatedAt = LocalDateTime.now()
    }

    companion object {
        fun of(request: CounselRequest): Counsel {
            return Counsel(
                name = request.name,
                phone = request.phone,
                email = request.email,
                memo = request.memo,
                address = request.address,
                zip = request.zip
            )
        }
    }
}