package com.quid.loan.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
class Counsel(
    @Id @GeneratedValue(strategy = IDENTITY) val counselId: Long? = null,
    val name: String,
    val phone: String,
    val email: String,
    var memo: String? = null,
    val address: String,
    val zip: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun updateMemo(memo: String) {
        this.memo = memo
        this.updatedAt = LocalDateTime.now()
    }

    companion object {
        fun of(
            name: String,
            phone: String,
            email: String,
            memo: String?,
            address: String,
            zip: String
        ): Counsel {
            return Counsel(
                name = name,
                phone = phone,
                email = email,
                memo = memo,
                address = address,
                zip = zip
            )
        }
    }
}