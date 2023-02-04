package com.quid.loan.counsel.service

import com.quid.loan.counsel.domain.Counsel
import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.counsel.dto.CounselResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CounselService {
    fun createCounsel(counselRequest: CounselRequest): CounselResponse
    fun getCounsel(counselId: Long): CounselResponse
    fun updateCounselMemo(counselId: Long, memo: String)
    fun deleteCounsel(counselId: Long)
    fun getCounsels(pageable : Pageable): Page<CounselResponse>
}