package com.quid.loan.loan.controller

import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.loan.service.LoanService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/loan")
class LoanController(private val loanService: LoanService) {

    @PostMapping("/{userSeq}")
    fun createLoan(@RequestBody request: LoanCreateRequest, @PathVariable userSeq: Long) {
        loanService.createLoan(userSeq, request)
    }
}