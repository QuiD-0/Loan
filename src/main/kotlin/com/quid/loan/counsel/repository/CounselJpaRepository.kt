package com.quid.loan.counsel.repository

import com.quid.loan.counsel.domain.Counsel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CounselJpaRepository : JpaRepository<Counsel, Long> {

    @Modifying
    @Query("update Counsel c set c.deletedAt = current_timestamp where c.counselId = :counselId")
    override fun deleteById(counselId: Long)

    fun existsByName(name: String): Boolean


}