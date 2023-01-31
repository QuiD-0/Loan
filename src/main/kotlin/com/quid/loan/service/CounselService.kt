package com.quid.loan.service

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest

interface CounselService {
    fun createCounsel(counselRequest: CounselRequest): Counsel
    fun getCounsel(counselId: Long): Counsel
    fun updateCounselMemo(counselId: Long, memo: String): Counsel
    fun deleteCounsel(counselId: Long)
    fun getCounsels(): List<Counsel>
}