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
    fun createCounsel(request: CounselRequest)

    @Service
    class CounselServiceImpl(
        private val counselRepository: CounselRepository,
        private val userRepository: UserRepository,
    ) : CounselService {
        private val logger = mu.KotlinLogging.logger {}

        @Transactional(readOnly = true)
        override fun getCounsel(counselId: Long): CounselResponse {
            logger.info { "getCounsel: $counselId" }
            return counselRepository.findById(counselId)
                .let { CounselResponse.of(it) }
        }

        @Transactional(readOnly = true)
        override fun getCounsels(pageable: Pageable): Page<CounselResponse> {
            logger.info { "getCounsels" }
            return counselRepository.getCounsels(pageable).map(CounselResponse.Companion::of)
        }

        @Transactional
        override fun updateCounselMemo(request: CounselUpdateRequest) {
            userRepository.findCounselByUserId(request.userSeq).updateMemo(request.memo)
        }

        @Transactional
        override fun deleteCounsel(id: Long) {
            val user = userRepository.findById(id)
            user.deleteCounsel()
        }

        @Transactional
        override fun createCounsel(request: CounselRequest) {
            val user = userRepository.findById(request.userSeq)
            if(user.hasCounsel()){
                fail(StatusCode.COUNSEL_ALREADY_EXIST_ERROR)
            }
            user.createCounsel(request)
        }
    }
}