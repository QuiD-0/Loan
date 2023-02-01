package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CounselJpaRepository : JpaRepository<Counsel, Long> {

    @Query("delete from Counsel c where c.counselId = :counselId")
    override fun deleteById(id: Long)
}