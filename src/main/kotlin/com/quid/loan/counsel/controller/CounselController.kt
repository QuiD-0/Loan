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
class CounselController(private val counselService: CounselService,
    private val userService: UserService)
{

    @GetMapping("/list")
    fun getCounsels(pageable: Pageable): Page<CounselResponse> {
        return counselService.getCounsels(pageable)
    }

    @GetMapping("/{id}")
    fun getCounsel(@PathVariable id: Long): CounselResponse {
        return counselService.getCounsel(id)
    }

    @PutMapping("/counsel/memo")
    fun updateCounselMemo(@RequestBody request: CounselUpdateRequest) {
        userService.updateCounselMemo(request)
    }

    @DeleteMapping("/counsel/{id}")
    fun deleteCounsel(@PathVariable id: Long) {
        userService.deleteCounsel(id)
    }

    @PostMapping("/counsel")
    fun createCounsel(@RequestBody request: CounselRequest): CounselResponse {
        return userService.createCounsel(request)
    }

}