package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CounselRepositoryImpl(private val counselJpaRepository: CounselJpaRepository) : CounselRepository {
    override fun createCounsel(counsel: Counsel): Counsel {
        return counselJpaRepository.save(counsel)
    }

    override fun findById(counselId: Long): Counsel {
        return counselJpaRepository.findByIdOrNull(counselId) ?: throw IllegalArgumentException("해당 상담이 존재하지 않습니다.")
    }

    override fun getCounsels(): List<Counsel> {
        return counselJpaRepository.findAll()
    }

    override fun deleteById(counselId: Long) {
        return counselJpaRepository.deleteById(counselId)
    }

}