package com.quid.loan.counsel.repository

import com.quid.loan.counsel.domain.Counsel
import org.springframework.data.jpa.repository.JpaRepository

interface CounselJpaRepository : JpaRepository<Counsel, Long> {

}