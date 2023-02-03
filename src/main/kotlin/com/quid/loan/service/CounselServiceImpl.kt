package com.quid.loan.service

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.dto.CounselResponse
import com.quid.loan.repository.CounselRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CounselServiceImpl(private val counselRepository: CounselRepository) : CounselService {
    @Transactional
    override
    fun createCounsel(counselRequest: CounselRequest): CounselResponse {
        val counsel = counselRepository.createCounsel(Counsel.of(counselRequest))
        return CounselResponse.of(counsel)
    }

    override
    fun getCounsel(counselId: Long): Counsel {
        return counselRepository.findById(counselId)
    }

    @Transactional
    override
    fun updateCounselMemo(counselId: Long, memo: String) {
        val findById = counselRepository.findById(counselId)
        findById.updateMemo(memo)
    }

    @Transactional
    override
    fun deleteCounsel(counselId: Long) {
        counselRepository.deleteById(counselId)
    }

    override
    fun getCounsels(): List<Counsel> {
        return counselRepository.getCounsels()
    }
}