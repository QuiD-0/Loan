package com.quid.loan.loan.controller

import com.quid.loan.loan.dto.LoanCreateRequest
import com.quid.loan.loan.dto.LoanResponse
import com.quid.loan.loan.dto.PayRequest
import com.quid.loan.loan.service.LoanService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/loan")
class LoanController(private val loanService: LoanService) {

    @PostMapping("/{userSeq}")
    fun createLoan(@RequestBody request: LoanCreateRequest, @PathVariable userSeq: Long) {
        loanService.createLoan(userSeq, request)
    }

    @PostMapping("/pay")
    fun pay(@RequestBody payRequest: PayRequest) {
        return loanService.pay(payRequest)
    }

    @GetMapping("/histories")
    fun getHistories() : Map<String, List<LoanResponse>> {
        return loanService.getHistories()
    }

    @GetMapping("/payedAmount")
    fun getPayedAmount() : Map<String, String> {
        return loanService.getPayedAmount()
    }

}