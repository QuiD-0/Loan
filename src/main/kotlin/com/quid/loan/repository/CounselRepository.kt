package com.quid.loan.repository

import com.quid.loan.domain.Counsel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CounselRepository {
    fun createCounsel(counsel: Counsel): Counsel
    fun findById(counselId: Long): Counsel
    fun getCounsels(pageable: Pageable): Page<Counsel>
    fun deleteById(counselId: Long)
    fun updateMemo(counselId: Long, memo: String)
    fun isExistCounsel(name: String): Boolean
}