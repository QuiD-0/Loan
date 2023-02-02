package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CounselJpaRepository : JpaRepository<Counsel, Long> {

    @Modifying
    @Query("update Counsel c set c.deletedAt = current_timestamp where c.counselId = :id")
    override fun deleteById(id: Long)
}