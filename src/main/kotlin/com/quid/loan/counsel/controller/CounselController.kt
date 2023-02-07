package com.quid.loan.counsel.controller

import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.counsel.service.CounselService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/counsel")
class CounselController(private val counselService: CounselService) {

    @GetMapping("/list")
    fun getCounsels(pageable: Pageable): Page<CounselResponse> {
        return counselService.getCounsels(pageable)
    }

    @GetMapping("/{id}")
    fun getCounsel(@PathVariable id: Long): CounselResponse {
        return counselService.getCounsel(id)
    }

}