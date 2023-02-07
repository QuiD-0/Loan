package com.quid.loan.counsel.service

import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.counsel.repository.CounselRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CounselService {
    fun getCounsel(counselId: Long): CounselResponse
    fun getCounsels(pageable: Pageable): Page<CounselResponse>

    @Service
    class CounselServiceImpl(
        private val counselRepository: CounselRepository,
    ) : CounselService {
        private val logger = mu.KotlinLogging.logger {}

        @Transactional(readOnly = true)
        override fun getCounsel(counselId: Long): CounselResponse {
            logger.info { "getCounsel: $counselId" }
            val counsel = counselRepository.findById(counselId)
            return CounselResponse.of(counsel)
        }

        @Transactional(readOnly = true)
        override fun getCounsels(pageable: Pageable): Page<CounselResponse> {
            logger.info { "getCounsels" }
            return counselRepository.getCounsels(pageable).map(CounselResponse.Companion::of)
        }
    }
}