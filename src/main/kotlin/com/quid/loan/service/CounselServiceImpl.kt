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
    override fun createCounsel(counselRequest: CounselRequest): CounselResponse {
        val counsel = counselRepository.createCounsel(counselRequest.toEntity())
        return CounselResponse.of(counsel)
    }

    override fun getCounsel(counselId: Long): Counsel {
        TODO("Not yet implemented")
    }

    override fun updateCounselMemo(counselId: Long, memo: String): Counsel {
        TODO("Not yet implemented")
    }

    override fun deleteCounsel(counselId: Long) {
        TODO("Not yet implemented")
    }

    override fun getCounsels(): List<Counsel> {
        TODO("Not yet implemented")
    }
}