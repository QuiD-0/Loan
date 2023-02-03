package com.quid.loan.service

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.dto.CounselResponse
import com.quid.loan.repository.CounselRepository
import com.quid.loan.utils.StatusCode
import com.quid.loan.utils.StatusCode.*
import com.quid.loan.utils.fail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CounselServiceImpl(private val counselRepository: CounselRepository) : CounselService {
    private val logger = mu.KotlinLogging.logger {}

    @Transactional
    override fun createCounsel(counselRequest: CounselRequest): CounselResponse {
        logger.info { "createCounsel: $counselRequest" }
        counselDuplicateCheck(counselRequest.name)
        val counsel = counselRepository.createCounsel(Counsel.of(counselRequest))
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
    override fun getCounsels(pageable : Pageable): Page<CounselResponse> {
        logger.info { "getCounsels" }
        return counselRepository.getCounsels(pageable).map(CounselResponse.Companion::of)
    }

    private fun counselDuplicateCheck(name: String) {
        if (counselRepository.isExistCounsel(name)) {
            fail(COUNSEL_DUPLICATE_ERROR)
        }
    }
}