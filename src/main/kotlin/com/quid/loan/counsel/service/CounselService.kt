package com.quid.loan.counsel.service

import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.counsel.dto.CounselUpdateRequest
import com.quid.loan.counsel.repository.CounselRepository
import com.quid.loan.user.repository.UserRepository
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CounselService {
    fun getCounsel(counselId: Long): CounselResponse
    fun getCounsels(pageable: Pageable): Page<CounselResponse>
    fun updateCounselMemo(request: CounselUpdateRequest)
    fun deleteCounsel(id: Long)
    fun createCounsel(request: CounselRequest): CounselResponse

    @Service
    class CounselServiceImpl(
        private val counselRepository: CounselRepository,
        private val userRepository: UserRepository,
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

        @Transactional
        override fun updateCounselMemo(request: CounselUpdateRequest) {
            userRepository.findById(request.userSeq).counsel?.updateMemo(request.memo)
                ?: fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
        }

        @Transactional
        override fun deleteCounsel(id: Long) {
            val user = userRepository.findById(id)
            user.counsel?.let { user.deleteCounsel() }
                ?: fail(StatusCode.COUNSEL_NOT_FOUND_ERROR)
        }

        @Transactional
        override fun createCounsel(request: CounselRequest): CounselResponse {
            val user = userRepository.findById(request.userSeq)
            if (user.counsel != null) fail(StatusCode.COUNSEL_ALREADY_EXIST_ERROR)
            user.counsel = user.createCounsel(request)
            return CounselResponse.of(user.counsel!!)
        }
    }
}