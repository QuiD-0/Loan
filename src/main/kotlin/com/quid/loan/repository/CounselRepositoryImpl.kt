package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import org.springframework.stereotype.Repository

@Repository
class CounselRepositoryImpl(private val counselJpaRepository: CounselJpaRepository) : CounselRepository {
    override fun createCounsel(counsel: Counsel): Counsel {
        return counselJpaRepository.save(counsel)
    }

}