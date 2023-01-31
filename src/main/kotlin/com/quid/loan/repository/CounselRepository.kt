package com.quid.loan.repository

import com.quid.loan.domain.Counsel

interface CounselRepository {
    fun createCounsel(counsel: Counsel) : Counsel
}