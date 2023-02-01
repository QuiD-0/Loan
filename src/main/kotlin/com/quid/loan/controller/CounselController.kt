package com.quid.loan.controller

import com.quid.loan.domain.Counsel
import com.quid.loan.dto.CounselRequest
import com.quid.loan.dto.CounselResponse
import com.quid.loan.service.CounselService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/counsel")
class CounselController(private val counselService: CounselService) {

    @GetMapping("/list")
    fun getCounsels(): List<Counsel> {
        return counselService.getCounsels()
    }

    @GetMapping("/{id}")
    fun getCounsel(@PathVariable id: Long): Counsel {
        return counselService.getCounsel(id)
    }

    @PostMapping
    fun createCounsel(@RequestBody counsel: CounselRequest): CounselResponse {
        return counselService.createCounsel(counsel)
    }
}