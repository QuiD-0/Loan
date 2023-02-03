package com.quid.loan.service

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.dto.CounselResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CounselService {
    fun createCounsel(counselRequest: CounselRequest): CounselResponse
    fun getCounsel(counselId: Long): CounselResponse
    fun updateCounselMemo(counselId: Long, memo: String)
    fun deleteCounsel(counselId: Long)
    fun getCounsels(pageable : Pageable): Page<CounselResponse>
}