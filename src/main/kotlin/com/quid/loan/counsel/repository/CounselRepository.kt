package com.quid.loan.counsel.repository

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

interface CounselRepository {
    fun createCounsel(counsel: Counsel): Counsel
    fun findById(counselId: Long): Counsel
    fun getCounsels(pageable: Pageable): Page<Counsel>
    fun deleteById(counselId: Long)
    fun updateMemo(counselId: Long, memo: String)


    @Repository
    class CounselRepositoryImpl(private val counselJpaRepository: CounselJpaRepository) :
        CounselRepository {
        override fun createCounsel(counsel: Counsel): Counsel {
            return counselJpaRepository.save(counsel)
        }

        override fun findById(counselId: Long): Counsel {
            return counselJpaRepository.findByIdOrNull(counselId) ?: fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
        }

        override fun getCounsels(pageable : Pageable): Page<Counsel> {
            return counselJpaRepository.findAll(pageable)
        }

        override fun deleteById(counselId: Long) {
            return counselJpaRepository.deleteById(counselId)
        }

        override fun updateMemo(counselId: Long, memo: String) {
            counselJpaRepository.findByIdOrNull(counselId)?.updateMemo(memo)
                ?: fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
        }
    }
}
