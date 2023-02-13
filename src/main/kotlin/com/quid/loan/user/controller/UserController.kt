package com.quid.loan.user.controller

import com.quid.loan.counsel.dto.CounselRequest
import com.quid.loan.counsel.dto.CounselResponse
import com.quid.loan.counsel.dto.CounselUpdateRequest
import com.quid.loan.user.dto.UserCreateRequest
import com.quid.loan.user.dto.UserResponse
import com.quid.loan.user.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun createUser(@RequestBody userCreateRequest: UserCreateRequest): UserResponse {
        return userService.createUser(userCreateRequest)
    }

    @GetMapping("/counsel/{id}")
    fun getCounsel(@PathVariable id: Long): CounselResponse {
        return userService.getUserCounsel(id)
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