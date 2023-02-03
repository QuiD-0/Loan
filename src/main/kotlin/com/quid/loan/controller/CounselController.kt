package com.quid.loan.controller

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.dto.CounselResponse
import com.quid.loan.dto.CounselUpdateRequest
import com.quid.loan.service.CounselService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/counsel")
class CounselController(private val counselService: CounselService) {

    @GetMapping("/list")
    fun getCounsels(pageable: Pageable): Page<Counsel> {
        return counselService.getCounsels(pageable)
    }

    @GetMapping("/{id}")
    fun getCounsel(@PathVariable id: Long): Counsel {
        return counselService.getCounsel(id)
    }

    @PostMapping
    fun createCounsel(@RequestBody counsel: CounselRequest): CounselResponse {
        return counselService.createCounsel(counsel)
    }

    @PutMapping("/memo")
    fun updateCounselMemo(@RequestBody request: CounselUpdateRequest) {
        counselService.updateCounselMemo(request.counselId, request.memo)
    }

    @DeleteMapping("/{id}")
    fun deleteCounsel(@PathVariable id: Long) {
        counselService.deleteCounsel(id)
    }
}