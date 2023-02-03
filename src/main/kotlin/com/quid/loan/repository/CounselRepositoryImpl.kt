package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import com.quid.loan.utils.fail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CounselRepositoryImpl(private val counselJpaRepository: CounselJpaRepository) :
    CounselRepository {
    override fun createCounsel(counsel: Counsel): Counsel {
        return counselJpaRepository.save(counsel)
    }

    override fun findById(counselId: Long): Counsel {
        return counselJpaRepository.findByIdOrNull(counselId) ?: fail("해당 상담이 존재하지 않습니다.")
    }

    override fun getCounsels(pageable : Pageable): Page<Counsel> {
        return counselJpaRepository.findAll(pageable)
    }

    override fun deleteById(counselId: Long) {
        return counselJpaRepository.deleteById(counselId)
    }

    override fun updateMemo(counselId: Long, memo: String) {
        counselJpaRepository.findByIdOrNull(counselId)?.updateMemo(memo)
            ?: fail("해당 상담이 존재하지 않습니다.")
    }

}