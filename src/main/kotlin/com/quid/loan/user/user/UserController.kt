package com.quid.loan.user.user

import com.quid.loan.counsel.dto.CounselResponse
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

}