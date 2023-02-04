package com.quid.loan.counsel.repository

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.StatusCode.COUNSEL_NOT_FOUND_ERROR
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
        return counselJpaRepository.findByIdOrNull(counselId) ?: fail(COUNSEL_NOT_FOUND_ERROR)
    }

    override fun getCounsels(pageable : Pageable): Page<Counsel> {
        return counselJpaRepository.findAll(pageable)
    }

    override fun deleteById(counselId: Long) {
        return counselJpaRepository.deleteById(counselId)
    }

    override fun updateMemo(counselId: Long, memo: String) {
        counselJpaRepository.findByIdOrNull(counselId)?.updateMemo(memo)
            ?: fail(COUNSEL_NOT_FOUND_ERROR)
    }

    override fun isExistCounsel(name: String): Boolean {
        return counselJpaRepository.existsByName(name)
    }

}