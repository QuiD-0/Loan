package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import org.springframework.data.jpa.repository.JpaRepository

interface CounselJpaRepository : JpaRepository<Counsel, Long> {
}