package com.quid.loan.service

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.repository.CounselRepository
import org.springframework.stereotype.Service

@Service
class CounselServiceImpl(private val counselRepository: CounselRepository) : CounselService {
    override fun createCounsel(counselRequest: CounselRequest): Counsel {
        return counselRepository.createCounsel(counselRequest.toEntity())
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