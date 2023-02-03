package com.quid.loan.repository

import com.quid.loan.domain.Counsel

interface CounselRepository {
    fun createCounsel(counsel: Counsel) : Counsel
    fun findById(counselId: Long): Counsel
    fun getCounsels(): List<Counsel>
    fun deleteById(counselId: Long)
    fun updateMemo(counselId: Long, memo: String)
}