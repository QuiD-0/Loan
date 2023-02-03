package com.quid.loan.service

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.dto.CounselResponse
import com.quid.loan.repository.CounselRepository
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
        val counsel = counselRepository.createCounsel(Counsel.of(counselRequest))
        return CounselResponse.of(counsel)
    }

    override fun getCounsel(counselId: Long): Counsel {
        logger.info { "getCounsel: $counselId" }
        return counselRepository.findById(counselId)
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

    override fun getCounsels(pageable : Pageable): Page<Counsel> {
        logger.info { "getCounsels" }
        return counselRepository.getCounsels(pageable)
    }
}