package com.quid.loan.counsel.controller

import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.counsel.dto.CounselUpdateRequest
import com.quid.loan.counsel.service.CounselService
import com.quid.loan.user.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/counsel")
class CounselController(private val counselService: CounselService)
{

    @GetMapping("/list")
    fun getCounsels(pageable: Pageable): Page<CounselResponse> {
        return counselService.getCounsels(pageable)
    }

    @GetMapping("/{id}")
    fun getCounsel(@PathVariable id: Long): CounselResponse {
        return counselService.getCounsel(id)
    }

    @PutMapping("/memo")
    fun updateCounselMemo(@RequestBody request: CounselUpdateRequest) {
        counselService.updateCounselMemo(request)
    }

    @DeleteMapping("/{id}")
    fun deleteCounsel(@PathVariable id: Long) {
        counselService.deleteCounsel(id)
    }

    @PostMapping
    fun createCounsel(@RequestBody request: CounselRequest): CounselResponse {
        return counselService.createCounsel(request)
    }

}