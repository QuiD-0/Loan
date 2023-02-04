package com.quid.loan.counsel.service

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.counsel.repository.CounselRepository
import com.quid.loan.user.repository.UserRepository
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.fail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CounselService {
    fun createCounsel(counselRequest: CounselRequest): CounselResponse
    fun getCounsel(counselId: Long): CounselResponse
    fun updateCounselMemo(counselId: Long, memo: String)
    fun deleteCounsel(counselId: Long)
    fun getCounsels(pageable: Pageable): Page<CounselResponse>

    @Service
    class CounselServiceImpl(
        private val counselRepository: CounselRepository,
        private val userRepository: UserRepository,
    ) : CounselService {
        private val logger = mu.KotlinLogging.logger {}

        @Transactional
        override fun createCounsel(counselRequest: CounselRequest): CounselResponse {
            logger.info { "createCounsel: $counselRequest" }
            val user = userRepository.findById(counselRequest.userId)
            if(user.counsel != null) fail(StatusCode.COUNSEL_ALREADY_EXIST_ERROR)
            val counsel = counselRepository.createCounsel(Counsel.of(counselRequest, user))
            return CounselResponse.of(counsel)
        }

        @Transactional(readOnly = true)
        override fun getCounsel(counselId: Long): CounselResponse {
            logger.info { "getCounsel: $counselId" }
            val counsel = counselRepository.findById(counselId)
            return CounselResponse.of(counsel)
        }

        @Transactional
        override fun updateCounselMemo(counselId: Long, memo: String) {
            logger.info { "updateCounselMemo: $counselId, $memo" }
            counselRepository.updateMemo(counselId, memo)
        }

        @Transactional
        override fun deleteCounsel(counselId: Long) {
            logger.info { "deleteCounsel: $counselId" }
            counselRepository.deleteById(counselId)
        }

        @Transactional(readOnly = true)
        override fun getCounsels(pageable: Pageable): Page<CounselResponse> {
            logger.info { "getCounsels" }
            return counselRepository.getCounsels(pageable).map(CounselResponse.Companion::of)
        }
    }
}